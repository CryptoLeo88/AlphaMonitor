package com.block.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.block.entity.OrganizationTypeRole;
import com.block.entity.Role;
import com.block.entity.RoleMenu;
import com.block.entity.UserRole;
import com.block.exception.CommonException;
import com.block.mapper.OrganizationTypeRoleMapper;
import com.block.mapper.RoleMapper;
import com.block.mapper.RoleMenuMapper;
import com.block.mapper.UserRoleMapper;
import com.block.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final OrganizationTypeRoleMapper organizationTypeRoleMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleMenuMapper roleMenuMapper;

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(OrganizationTypeRoleMapper organizationTypeRoleMapper, UserRoleMapper userRoleMapper, RoleMenuMapper roleMenuMapper, RoleMapper roleMapper) {
        this.organizationTypeRoleMapper = organizationTypeRoleMapper;

        this.userRoleMapper = userRoleMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public Boolean delete(Integer id) {
        //OrganizationTypeRole
        QueryWrapper<OrganizationTypeRole> otrwrapper = new QueryWrapper<>();
        otrwrapper.eq("role_id",id);
        List<OrganizationTypeRole> organizationTypeRoles = organizationTypeRoleMapper.selectList(otrwrapper);

        //UserRole
        QueryWrapper<UserRole> userwrapper = new QueryWrapper<>();
        userwrapper.eq("role_id",id);
        List<UserRole> userRoles = userRoleMapper.selectList(userwrapper);

        //RoleMenu
        QueryWrapper<RoleMenu> rolwrapper = new QueryWrapper<>();
        rolwrapper.eq("role_id",id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(rolwrapper);

        if(organizationTypeRoles.size() != 0){
            throw new CommonException("删除失败，与表organization_type_role有关联！");
        }else {
            if(userRoles.size() != 0){
                throw new CommonException("删除失败，与表user_role有关联！");
            }else {
                if(roleMenus.size() != 0){
                    throw new CommonException("删除失败，与表role_menu有关联！");
                }else {
                    System.out.println("删除成功！");
                    this.removeById(id);
                }
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Map<String, Object>> findIdAndName() {
        return roleMapper.findIdAndName();
    }

    @Override
    public IPage<Role> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<Role> wrapper = new QueryWrapper<Role>()
                .like(StringUtils.isNotBlank(search.get("roleName")),"role_name",search.get("roleName").trim());
        return roleMapper.list(new Page<>(page, size), wrapper);
    }
}
