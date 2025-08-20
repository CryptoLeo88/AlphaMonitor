package com.block.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.Product;
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
public interface ProductService extends IService<Product> {


    IPage<Product> page(Integer page, Integer size, Map<String, String> search);
}
