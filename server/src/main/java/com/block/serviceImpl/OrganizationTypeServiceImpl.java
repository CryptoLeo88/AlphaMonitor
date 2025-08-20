package com.block.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.block.entity.Organization;
import com.block.entity.OrganizationType;
import com.block.entity.OrganizationTypeRole;
import com.block.exception.CommonException;
import com.block.mapper.OrganizationMapper;
import com.block.mapper.OrganizationTypeMapper;
import com.block.mapper.OrganizationTypeRoleMapper;
import com.block.service.OrganizationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构类型 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationTypeServiceImpl extends ServiceImpl<OrganizationTypeMapper, OrganizationType> implements OrganizationTypeService {

    private final OrganizationMapper organizationMapper;
    private final OrganizationTypeMapper organizationTypeMapper;
    private final OrganizationTypeRoleMapper organizationTypeRoleMapper;

    @Autowired
    public OrganizationTypeServiceImpl(OrganizationMapper organizationMapper, OrganizationTypeMapper organizationTypeMapper, OrganizationTypeRoleMapper organizationTypeRoleMapper) {
        this.organizationMapper = organizationMapper;
        this.organizationTypeMapper = organizationTypeMapper;
        this.organizationTypeRoleMapper = organizationTypeRoleMapper;
    }

    @Override
    public Boolean delete(Integer id) {
        //organization
        QueryWrapper<Organization> orgwrapper = new QueryWrapper<>();
        orgwrapper.eq("organization_type_id",id);
        List<Organization> organizations = organizationMapper.selectList(orgwrapper);

        //OrganizationTypeRole
        QueryWrapper<OrganizationTypeRole> otywrapper = new QueryWrapper<>();
        otywrapper.eq("organization_type_id",id);
        List<OrganizationTypeRole> organizationTypeRoles = organizationTypeRoleMapper.selectList(otywrapper);


        if(organizations.size() != 0){
            throw new CommonException("删除失败，与表organization有关联！");
        }else {
            if(organizationTypeRoles.size() != 0){
                throw new CommonException("删除失败，与表organization_type_role有关联！");
            }else {
                System.out.println("删除成功！");
                this.removeById(id);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Map<String, Object>> findIdAndName() {
        return organizationTypeMapper.findIdAndName();
    }
}
