package com.block.controller;


import com.block.entity.User;
import com.block.service.UserService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "当前用户信息")
    @GetMapping("/info")
    public Result<User> currInfo() {
        Result<User> result = new Result<User>(StatusCode.Success);
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            result.setData(user);
        } catch (Exception e) {
            return new Result<User>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "查询所有用户信息")
    @PostMapping("/findAll")
    public Result<IPage<User>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<User>> result = new Result<IPage<User>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<User> users = userService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(users);
            return result;

        } catch (Exception e) {
            return new Result<IPage<User>>(-1, e.getMessage());
        }
    }


    @ApiOperation(value = "根据id查询用户信息")
    @PostMapping("/findById/{id}")
    @ApiImplicitParam(name = "id", required = true, value = "用户ID", dataTypeClass = Integer.class)
    public Result<User> findById(@PathVariable Integer id) {
        Result<User> result = new Result<User>(StatusCode.Success);
        try {
            User user = userService.getById(id);
            result.setData(user);
        } catch (Exception e) {
            return new Result<User>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加用户信息")
    @PostMapping("/add")
    @ApiImplicitParam(name = "user", value = "json格式的用户对象", required = true)
    public Result<Boolean> add(@RequestBody User user) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = userService.add(user);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除用户信息")
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "要删除的用户id的值", required = true)
    public Result<String> delete(@PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            userService.delete(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody User user) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        user.setCreateTime(LocalDateTime.now());
        try {
            Boolean bool = userService.updateById(user);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }
}

