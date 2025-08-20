package com.block.controller;


import com.block.entity.OrganizationType;
import com.block.service.OrganizationTypeService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构类型 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "机构类型信息")
@RestController
@RequestMapping("/organizationType")
public class OrganizationTypeController {
    private final OrganizationTypeService organizationTypeService;

    @Autowired
    public OrganizationTypeController(OrganizationTypeService organizationTypeService) {
        this.organizationTypeService = organizationTypeService;
    }

    @ApiOperation(value = "查询所有机构类型信息")
    @PostMapping("/findAll")

    public Result<IPage<OrganizationType>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<OrganizationType>> result = new Result<IPage<OrganizationType>>(StatusCode.Success);
        try {
            IPage<OrganizationType> organizationTypes = organizationTypeService.page(new Page<>(pageUtil.getPage(), pageUtil.getSize()));
            result.setData(organizationTypes);
            return result;
        } catch (Exception e) {
            return new Result<IPage<OrganizationType>>(StatusCode.Fail);
        }

    }


    @ApiOperation(value = "根据id查询机构类型信息")
    @PostMapping("/findById/{id}")
    public Result<OrganizationType> findById(@PathVariable Integer id) {
        Result<OrganizationType> result = new Result<OrganizationType>(StatusCode.Success);
        try {
            OrganizationType organizationType = organizationTypeService.getById(id);
            result.setData(organizationType);
        } catch (Exception e) {
            return new Result<OrganizationType>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加机构类型信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "机构类型对象", value = "传入json格式", required = true) @Validated OrganizationType organizationType, BindingResult bindingResult) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationTypeService.saveOrUpdate(organizationType);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除机构类型信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "机构类型ID", value = "要删除的机构类型id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            organizationTypeService.delete(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新机构类型信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "机构类型对象", value = "传入json格式", required = true) @Validated OrganizationType organizationType, BindingResult bindingResult) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationTypeService.updateById(organizationType);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

    @ApiOperation(value = "查询机构类型的id和名称信息")
    @PostMapping("/findIdAndName")
    public Result<List<Map<String, Object>>> findIdAndName() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>(StatusCode.Success);
        try {
            List<Map<String, Object>> lists = organizationTypeService.findIdAndName();
            result.setData(lists);
            return result;
        } catch (Exception e) {
            return new Result<List<Map<String, Object>>>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}

