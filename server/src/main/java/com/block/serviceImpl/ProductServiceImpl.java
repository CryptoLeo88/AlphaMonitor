package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.Product;
import com.block.mapper.ProductMapper;
import com.block.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2023-04-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    @Override
    public IPage<Product> page(Integer page, Integer size, Map<String, String> search) {
        return productMapper.selectPage(new Page<>(page, size), null);
    }
}
