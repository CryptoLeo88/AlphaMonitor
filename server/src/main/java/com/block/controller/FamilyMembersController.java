package com.block.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.FamilyMembers;
import com.block.service.FamilyMembersService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.Api;
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
 * @since 2023-04-28
 */
@Api("家庭成员")
@RestController
@RequestMapping("/familyMembers")
public class FamilyMembersController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final FamilyMembersService familyMembersService;

    @Autowired
    public FamilyMembersController(FamilyMembersService familyMembersService) {
        this.familyMembersService = familyMembersService;
    }

    @ApiOperation(value = "查询所有家庭成员信息page")
    @PostMapping("/findAll")
    public Result<IPage<FamilyMembers>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<FamilyMembers>> result = new Result<IPage<FamilyMembers>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<FamilyMembers> familyMemberss = familyMembersService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(familyMemberss);
            return result;
        } catch (Exception e) {
            return new Result<IPage<FamilyMembers>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有家庭成员信息")
    @PostMapping("/list")

    public Result<List<FamilyMembers>> list() {
        Result<List<FamilyMembers>> result = new Result<List<FamilyMembers>>(StatusCode.Success);
        try {
            List<FamilyMembers> familyMemberss = familyMembersService.list();
            result.setData(familyMemberss);
            return result;
        } catch (Exception e) {
            return new Result<List<FamilyMembers>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询家庭成员表信息")
    @PostMapping("/findById/{id}")
    public Result<FamilyMembers> findById(@PathVariable Integer id) {
        Result<FamilyMembers> result = new Result<FamilyMembers>(StatusCode.Success);
        try {
            FamilyMembers familyMembers = familyMembersService.getById(id);
            result.setData(familyMembers);
        } catch (Exception e) {
            return new Result<FamilyMembers>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加家庭成员表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "家庭成员表对象", value = "传入json格式", required = true) FamilyMembers familyMembers) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = familyMembersService.saveOrUpdate(familyMembers);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除家庭成员表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "家庭成员表ID", value = "要删除的家庭成员表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            familyMembersService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新家庭成员表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "家庭成员表对象", value = "传入json格式", required = true) FamilyMembers familyMembers) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = familyMembersService.updateById(familyMembers);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

}

