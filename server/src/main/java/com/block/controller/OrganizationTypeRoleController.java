package com.block.controller;


import com.block.entity.OrganizationTypeRole;
import com.block.service.OrganizationTypeRoleService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 机构类型角色 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "机构类型角色信息")
@RestController
@RequestMapping("/organizationTypeRole")
public class OrganizationTypeRoleController {
    private final OrganizationTypeRoleService organizationTypeRoleService;

    @Autowired
    public OrganizationTypeRoleController(OrganizationTypeRoleService organizationTypeRoleService) {
        this.organizationTypeRoleService = organizationTypeRoleService;
    }

    @ApiOperation(value = "查询所有机构类型角色信息")
    @PostMapping("/findAll")

    public Result<IPage<OrganizationTypeRole>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<OrganizationTypeRole>> result = new Result<IPage<OrganizationTypeRole>>(StatusCode.Success);
        try {
            IPage<OrganizationTypeRole> organizationTypeRoles = organizationTypeRoleService.page(new Page<>(pageUtil.getPage(), pageUtil.getSize()));
            result.setData(organizationTypeRoles);
            return result;
        } catch (Exception e) {
            return new Result<IPage<OrganizationTypeRole>>(StatusCode.Fail);
        }

    }


    @ApiOperation(value = "根据id查询机构类型角色信息")
    @PostMapping("/findById/{id}")
    public Result<OrganizationTypeRole> findById(@PathVariable Integer id) {
        Result<OrganizationTypeRole> result = new Result<OrganizationTypeRole>(StatusCode.Success);
        try {
            OrganizationTypeRole organizationTypeRole = organizationTypeRoleService.getById(id);
            result.setData(organizationTypeRole);
        } catch (Exception e) {
            return new Result<OrganizationTypeRole>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加机构类型角色信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "机构类型角色对象", value = "传入json格式", required = true) OrganizationTypeRole organizationTypeRole) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationTypeRoleService.saveOrUpdate(organizationTypeRole);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除机构类型角色信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "机构类型角色ID", value = "要删除的机构类型角色id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            organizationTypeRoleService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新机构类型角色信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "机构类型角色对象", value = "传入json格式", required = true) OrganizationTypeRole organizationTypeRole) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationTypeRoleService.updateById(organizationTypeRole);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }
}

