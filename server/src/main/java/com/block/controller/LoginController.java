package com.block.controller;


import com.block.utils.Result;
import com.block.utils.ShiroUtil;
import com.block.utils.StatusCode;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(tags = "用户登录")
@RestController
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", required = true, value = "用户名", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "password", required = true, value = "用户密码", dataTypeClass = String.class, paramType = "query"),
    })
    public Result<Map<String, Object>> login(String username, String password) {

        LOGGER.info(username);

        Result<Map<String, Object>> result = new Result<Map<String, Object>>(StatusCode.Success);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            Map<String, Object> map = new HashMap<>();
            map.put("token", subject.getSession().getId().toString());
            map.put("id", ShiroUtil.getUser().getId());
            map.put("username", ShiroUtil.getUser().getUsername());
            result.setData(map);
            return result;
        } catch (UnknownAccountException e) {
            return new Result<Map<String, Object>>(StatusCode.AccountValidateFail);
        } catch (IncorrectCredentialsException e) {
            return new Result<Map<String, Object>>(StatusCode.PasswordNotMatch);
        }
    }

    @GetMapping("/logout")
    public Result<String> logout() {
        Result<String> result = new Result<String>(StatusCode.Success);
        //销毁当前的shiro的用户session
        try {
            ShiroUtil.logout();
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail, e.getMessage());
        }
        return result;
    }
}
