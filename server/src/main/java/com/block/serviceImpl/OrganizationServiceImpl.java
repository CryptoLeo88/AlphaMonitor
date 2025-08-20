package com.block.serviceImpl;


import com.block.entity.Organization;
import com.block.entity.OrganizationProduct;
import com.block.mapper.OrganizationMapper;
import com.block.mapper.OrganizationProductMapper;
import com.block.service.OrganizationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    private final OrganizationProductMapper organizationProductMapper;



    private final OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationProductMapper organizationProductMapper,OrganizationMapper organizationMapper) {
        this.organizationProductMapper = organizationProductMapper;
        this.organizationMapper = organizationMapper;
    }


    @Override
    public Boolean delete(Integer id) {
        //OrganizationProduct
        QueryWrapper<OrganizationProduct> orgwrapper = new QueryWrapper<>();
        orgwrapper.eq("organization_id",id);
        List<OrganizationProduct> organizationProducts = organizationProductMapper.selectList(orgwrapper);

        return Boolean.TRUE;
    }

    @Override

    public List<Map<String, Object>> findIdAndName() {
        return organizationMapper.findIdAndName();
    }

    @Override
    public IPage<Organization> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<Organization> wrapper = new QueryWrapper<Organization>()
                .like(StringUtils.isNotBlank(search.get("name")),"name",search.get("name").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("contact")),"contact",search.get("contact").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("phoneNumber")),"phone_number",search.get("phoneNumber").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("organizationType")),"organization_type",search.get("organizationType").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("approvalStatus")),"approval_status",search.get("approvalStatus").trim())
                .or()
                .like(StringUtils.isNotBlank(search.get("status")),"status",search.get("status").trim());
        return organizationMapper.list(new Page<>(page, size), wrapper);
    }

    @Override
    public IPage<Organization> page1(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<Organization> wrapper = new QueryWrapper<Organization>()
                .like(StringUtils.isNotBlank(search.get("name")),"name",search.get("name").trim())
                .like(StringUtils.isNotBlank(search.get("contact")),"contact",search.get("contact").trim())
                .like(StringUtils.isNotBlank(search.get("phoneNumber")),"phone_number",search.get("phoneNumber").trim())
                .like(StringUtils.isNotBlank(search.get("organizationType")),"organizationType",search.get("organizationType").trim())
                //.like(StringUtils.isNotBlank(search.get("status")),"status",search.get("status").trim())
                .eq("status",Integer.parseInt(search.get("status").trim()));
        return organizationMapper.list(new Page<>(page, size), wrapper);
    }


}
