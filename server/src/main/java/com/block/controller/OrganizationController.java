package com.block.controller;


;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.Organization;
import com.block.service.OrganizationService;
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
 * 机构表 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "机构表信息")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "查询所有机构表信息")
    @PostMapping("/list")
    public Result<List<Organization>> list() {
        Result<List<Organization>> result = new Result<List<Organization>>(StatusCode.Success);
        try {
            List<Organization> organizations = organizationService.list();
            result.setData(organizations);
            return result;
        } catch (Exception e) {
            return new Result<List<Organization>>(StatusCode.Fail);
        }

    }
    @ApiOperation(value = "查询所有机构信息")
    @PostMapping("/findAll")

    public Result<IPage<Organization>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<Organization>> result = new Result<IPage<Organization>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<Organization> organizations = organizationService.page1(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(organizations);
            return result;
        } catch (Exception e) {
            return new Result<IPage<Organization>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询机构表信息")
    @PostMapping("/findById/{id}")
    public Result<Organization> findById(@PathVariable Integer id) {
        Result<Organization> result = new Result<Organization>(StatusCode.Success);
        try {
            Organization organization = organizationService.getById(id);
            result.setData(organization);
        } catch (Exception e) {
            return new Result<Organization>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加机构表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "机构表对象", value = "传入json格式", required = true) Organization organization) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationService.saveOrUpdate(organization);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除机构表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "机构表ID", value = "要删除的机构表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            organizationService.delete(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新机构表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "机构表对象", value = "传入json格式", required = true) Organization organization) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = organizationService.updateById(organization);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

    @ApiOperation(value = "查询机构的id和名称信息")
    @PostMapping("/findIdAndName")
    public Result<List<Map<String, Object>>> findIdAndName() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>(StatusCode.Success);
        try {
            List<Map<String, Object>> lists = organizationService.findIdAndName();
            result.setData(lists);
            return result;
        } catch (Exception e) {
            return new Result<List<Map<String, Object>>>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}

