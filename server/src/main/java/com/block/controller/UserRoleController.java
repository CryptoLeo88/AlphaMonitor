package com.block.controller;


import com.block.entity.UserRole;
import com.block.service.UserRoleService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "用户角色信息")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @ApiOperation(value = "根据id查询用户角色信息")
    @PostMapping("/findById/{id}")
    public Result<UserRole> findById(@PathVariable Integer id) {
        LOGGER.info(String.valueOf(id));
        Result<UserRole> result = new Result<UserRole>(StatusCode.Success);
        try {
            UserRole userRole = userRoleService.getById(id);
            result.setData(userRole);
        } catch (Exception e) {
            return new Result<UserRole>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "查询所有用户角色信息")
    @PostMapping("/findAll")

    public Result<IPage<UserRole>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<UserRole>> result = new Result<IPage<UserRole>>(StatusCode.Success);
        try {
            IPage<UserRole> userRoles = userRoleService.page(new Page<>(pageUtil.getPage(), pageUtil.getSize()));
            result.setData(userRoles);
            return result;
        } catch (Exception e) {
            return new Result<IPage<UserRole>>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "根据id删除用户角色信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            userRoleService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "添加用户角色信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "用户角色对象", value = "传入json格式", required = true) UserRole userRole) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = userRoleService.saveOrUpdate(userRole);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

    @ApiOperation(value = "根据用户id查询所有角色名称")
    @PostMapping("/findRoleById/{id}")
    public Result<List<String>> findRoleById(@PathVariable Integer id){
        Result<List<String>> result = new Result<List<String>>(StatusCode.Success);
        try {
            List<String> userRoles = userRoleService.getRoleById(id);
            result.setData(userRoles);
        } catch (Exception e) {
            return new Result<List<String>>(StatusCode.Fail);
        }
        return result;
    }

    @ApiOperation(value = "更新用户角色信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "用户角色对象", value = "传入json格式", required = true) UserRole userRole) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = userRoleService.updateById(userRole);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

}

