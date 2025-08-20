package com.block.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrfc
 * @since 2023-04-22
 */
public interface ProductTypeService extends IService<ProductType> {

    IPage<ProductType> page(Integer page, Integer size, Map<String, String> search);
}
