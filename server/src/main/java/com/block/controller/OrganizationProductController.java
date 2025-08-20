package com.block.controller;


import com.block.entity.OrganizationProduct;
import com.block.service.OrganizationProductService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 机构产品 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "机构产品信息")
@RestController
@RequestMapping("/organizationProduct")
public class OrganizationProductController {
    private final OrganizationProductService organizationProductService;

    @Autowired
    public OrganizationProductController(OrganizationProductService organizationProductService) {
        this.organizationProductService = organizationProductService;
    }

    @ApiOperation(value = "查询所有机构产品信息")
    @PostMapping("/findAll")
    public Result<IPage<OrganizationProduct>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<OrganizationProduct>> result = new Result<IPage<OrganizationProduct>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<OrganizationProduct> organizationProducts = organizationProductService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(organizationProducts);
            return result;
        } catch (Exception e) {
            return new Result<IPage<OrganizationProduct>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询机构产品信息")
    @PostMapping("/findById/{id}")
    public Result<OrganizationProduct> findById(@PathVariable Integer id) {
        Result<OrganizationProduct> result = new Result<OrganizationProduct>(StatusCode.Success);
        try {
            OrganizationProduct organizationProduct = organizationProductService.getById(id);
            result.setData(organizationProduct);
        } catch (Exception e) {
            return new Result<OrganizationProduct>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加机构产品信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "机构产品对象", value = "传入json格式", required = true) OrganizationProduct organizationProduct) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationProductService.saveOrUpdate(organizationProduct);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除机构产品信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "机构产品ID", value = "要删除的机构产品id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            organizationProductService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新机构产品信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "机构产品对象", value = "传入json格式", required = true) OrganizationProduct organizationProduct) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationProductService.updateById(organizationProduct);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }
}

