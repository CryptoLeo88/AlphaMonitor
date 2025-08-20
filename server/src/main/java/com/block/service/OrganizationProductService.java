package com.block.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.block.entity.OrganizationProduct;

import java.util.Map;

/**
 * <p>
 * 机构产品 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface OrganizationProductService extends IService<OrganizationProduct> {
    IPage<OrganizationProduct> page(Integer page, Integer size, Map<String, String> search);
}
