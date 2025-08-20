package com.block.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.Product;
import com.block.service.ProductService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.*;
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
@Api(tags = "产品管理")
@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "查询所有产品信息page")
    @PostMapping("/findAll")

    public Result<IPage<Product>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<Product>> result = new Result<IPage<Product>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<Product> products = productService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(products);
            return result;
        } catch (Exception e) {
            return new Result<IPage<Product>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有产品信息")
    @PostMapping("/list")

    public Result<List<Product>> list() {
        Result<List<Product>> result = new Result<List<Product>>(StatusCode.Success);
        try {
            List<Product> products = productService.list();
            result.setData(products);
            return result;
        } catch (Exception e) {
            return new Result<List<Product>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询产品表信息")
    @PostMapping("/findById/{id}")
    public Result<Product> findById(@PathVariable Integer id) {
        Result<Product> result = new Result<Product>(StatusCode.Success);
        try {
            Product product = productService.getById(id);
            result.setData(product);
        } catch (Exception e) {
            return new Result<Product>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加产品表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "产品表对象", value = "传入json格式", required = true) Product product) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = productService.saveOrUpdate(product);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除产品表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "产品表ID", value = "要删除的产品表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            productService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新产品表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "产品表对象", value = "传入json格式", required = true) Product product) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = productService.updateById(product);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

//    @ApiOperation(value = "查询产品的id和名称信息")
//    @PostMapping("/findIdAndName")
//    public Result<List<Map<String, Object>>> findIdAndName() {
//        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>(StatusCode.Success);
//        try {
//            List<Map<String, Object>> lists = productService.findIdAndName();
//            result.setData(lists);
//            return result;
//        } catch (Exception e) {
//            return new Result<List<Map<String, Object>>>(StatusCode.Fail.getCode(),e.getMessage());
//        }
//    }
}

