package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.ProductType;
import com.block.mapper.ProductTypeMapper;
import com.block.service.ProductTypeService;
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
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {
    private final ProductTypeMapper productTypeMapper;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeMapper productTypeMapper){
        this.productTypeMapper = productTypeMapper;
    }

    @Override
    public IPage<ProductType> page(Integer page, Integer size, Map<String, String> search) {
        return productTypeMapper.selectPage(new Page<>(page, size), null);
    }
}
