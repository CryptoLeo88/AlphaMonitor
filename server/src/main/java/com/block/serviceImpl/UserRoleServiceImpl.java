package com.block.serviceImpl;

import com.block.entity.UserRole;
import com.block.mapper.UserRoleMapper;
import com.block.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<String> getRoleById(Integer id) {
        return userRoleMapper.getRoleById(id);
    }

    @Override
    public List<String> getRoleNameById(Integer id) {
        return userRoleMapper.getRoleNameById(id);
    }

    //维护用户~角色的关联关系
    @Override
    public void saveOrUpdate(Integer id, List<Integer> roleIdList) {
        //需要先清除旧的关联数据，再插入新的关联信息
        this.remove(new QueryWrapper<UserRole>().eq("user_id", id));

        if (roleIdList != null && !roleIdList.isEmpty()) {
            UserRole userRole;
            for (Integer rId : roleIdList) {
                userRole = new UserRole();
                userRole.setRoleId(rId);
                userRole.setUserId(id);
                this.save(userRole);
            }
        }
    }
}
