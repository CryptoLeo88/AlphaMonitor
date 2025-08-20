package com.block.serviceImpl;

import com.block.entity.OrganizationProduct;
import com.block.mapper.OrganizationProductMapper;
import com.block.service.OrganizationProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 机构产品 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationProductServiceImpl extends ServiceImpl<OrganizationProductMapper, OrganizationProduct> implements OrganizationProductService {

    private final OrganizationProductMapper organizationProductMapper;

    public OrganizationProductServiceImpl(OrganizationProductMapper organizationProductMapper) {
        this.organizationProductMapper = organizationProductMapper;
    }

    @Override
    public IPage<OrganizationProduct> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<OrganizationProduct> wrapper = new QueryWrapper<OrganizationProduct>()
                .like(StringUtils.isNotBlank(search.get("productKey")),"pr.product_key",search.get("productKey").trim());
        return organizationProductMapper.list(new Page<>(page, size), wrapper);
    }
}
