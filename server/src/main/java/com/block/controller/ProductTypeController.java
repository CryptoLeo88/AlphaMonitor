package com.block.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.ProductType;
import com.block.service.ProductTypeService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @ApiOperation(value = "查询所有产品种类信息page")
    @PostMapping("/findAll")

    public Result<IPage<ProductType>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<ProductType>> result = new Result<IPage<ProductType>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<ProductType> productTypes = productTypeService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(productTypes);
            return result;
        } catch (Exception e) {
            return new Result<IPage<ProductType>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有产品种类信息")
    @PostMapping("/list")

    public Result<List<ProductType>> list() {
        Result<List<ProductType>> result = new Result<List<ProductType>>(StatusCode.Success);
        try {
            List<ProductType> productTypes = productTypeService.list();
            result.setData(productTypes);
            return result;
        } catch (Exception e) {
            return new Result<List<ProductType>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询产品种类表信息")
    @PostMapping("/findById/{id}")
    public Result<ProductType> findById(@PathVariable Integer id) {
        Result<ProductType> result = new Result<ProductType>(StatusCode.Success);
        try {
            ProductType productType = productTypeService.getById(id);
            result.setData(productType);
        } catch (Exception e) {
            return new Result<ProductType>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加产品种类表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "产品种类表对象", value = "传入json格式", required = true) ProductType productType) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = productTypeService.saveOrUpdate(productType);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除产品种类表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "产品种类表ID", value = "要删除的产品种类表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            productTypeService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新产品种类表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "产品种类表对象", value = "传入json格式", required = true) ProductType productType) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = productTypeService.updateById(productType);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

}

