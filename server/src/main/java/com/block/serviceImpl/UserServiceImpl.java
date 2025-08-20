package com.block.serviceImpl;

import com.block.entity.User;
import com.block.entity.UserRole;
import com.block.exception.CommonException;
import com.block.mapper.UserMapper;
import com.block.mapper.UserRoleMapper;
import com.block.service.UserService;
import com.block.utils.ShiroUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRoleMapper userRoleMapper, UserMapper userMapper) {
        this.userRoleMapper = userRoleMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Boolean add(User user) {
        if (this.getOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null) {
            throw new RuntimeException("用户名已存在!");
        }

        user.setCreateTime(LocalDateTime.now());

        if(user.getPassword()== null){
            user.setPassword("123456");
        }

        //加密密码串
        String salt= RandomStringUtils.randomAlphanumeric(20);
        String password= ShiroUtil.sha256(user.getPassword(),salt);
        user.setPassword(password);
        user.setSalt(salt);

        this.save(user);

        //维护好用户~角色的关联关系
        //需要先清除旧的关联数据，再插入新的关联信息
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", user.getId()));

        UserRole userRole;
        if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {

            for (Integer rId : user.getRoleIdList()) {
                userRole = new UserRole();
                userRole.setRoleId(rId);
                userRole.setUserId(user.getId());
                userRoleMapper.insert(userRole);
            }
        }else{//请求没有给角色信息默认一般用户，即4号
            userRole = new UserRole();
            userRole.setRoleId(4);
            userRole.setUserId(user.getId());
            userRoleMapper.insert(userRole);
        }

//        //维护好用户~岗位的关联关系
//        sysUserPostService.saveOrUpdate(entity.getUserId(),entity.getPostIdList());
        return Boolean.TRUE;
    }

    @Override

    public User findOne(QueryWrapper<User> wrapper) {
        User user = this.getOne(wrapper);
        List<String> roleNames = userRoleMapper.getRoleById(user.getId());
        LOGGER.info("====={}",roleNames.toString());
        user.setRoles(roleNames);
        return user;
    }

    @Override
    public Boolean delete(Integer id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",id);
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        if(userRoles.size() != 0){
            throw new CommonException("删除失败，与表user_role有关联！");
        }else {
            System.out.println("删除成功！");
            this.removeById(id);
        }
        return Boolean.TRUE;
    }

    @Override
    public IPage<User> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .like(StringUtils.isNotBlank(search.get("username")),"username",search.get("username").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("mobile")),"mobile",search.get("mobile").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("organizationName")),"organization_name",search.get("organizationName").trim());
        return userMapper.list(new Page<>(page, size), wrapper);
    }
}
