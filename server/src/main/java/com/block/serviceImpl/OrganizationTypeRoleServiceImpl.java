package com.block.serviceImpl;

import com.block.entity.OrganizationTypeRole;
import com.block.mapper.OrganizationTypeRoleMapper;
import com.block.service.OrganizationTypeRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 机构类型角色 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationTypeRoleServiceImpl extends ServiceImpl<OrganizationTypeRoleMapper, OrganizationTypeRole> implements OrganizationTypeRoleService {

}
