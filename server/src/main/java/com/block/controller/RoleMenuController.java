package com.block.controller;



import com.block.entity.RoleMenu;
import com.block.service.RoleMenuService;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    private final RoleMenuService roleMenuService;

    @Autowired
    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }


    @ApiOperation(value = "添加菜单信息")
    @PostMapping("/add")
    public Result<String> add(@RequestBody @ApiParam(name = "角色菜单", value = "传入json格式", required = true) Map <String,Object> roleMenuMap) {
        Result<String> result = new Result<String>(StatusCode.Success);

        try {
            Integer roleId= (Integer) roleMenuMap.get("roleId");
            List<Integer> menuIds= (List<Integer>) roleMenuMap.get("menuIds");
            for( Integer menuId:menuIds){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuService.save(roleMenu);
            }




            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

}

