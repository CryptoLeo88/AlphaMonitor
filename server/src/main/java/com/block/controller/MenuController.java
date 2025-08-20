package com.block.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.Menu;
import com.block.service.MenuService;
import com.block.utils.ConstantUtils;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "菜单信息")
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @ApiOperation(value = "查询所有菜单信息")
    @PostMapping("/findAll")
    public Result<IPage<Menu>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<Menu>> result = new Result<IPage<Menu>>(StatusCode.Success);
        try {
            IPage<Menu> menus = menuService.page(pageUtil.getPage(),pageUtil.getSize());
            result.setData(menus);
            return result;
        } catch (Exception e) {
            return new Result<IPage<Menu>>(StatusCode.Fail);
        }

    }


    @ApiOperation(value = "根据id查询菜单信息")
    @PostMapping("/findById/{id}")
    public Result<Menu> findById(@PathVariable Integer id) {
        Result<Menu> result = new Result<Menu>(StatusCode.Success);
        try {
            Menu menu = menuService.getById(id);
            result.setData(menu);
        } catch (Exception e) {
            return new Result<Menu>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加菜单信息")
    @PostMapping("/add")
    public Result<String> add(@RequestBody @ApiParam(name = "菜单对象", value = "传入json格式", required = true) Menu menu) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {

            String validate=this.validateForm(menu);
            if (StringUtils.isNotBlank(validate)){
                return new Result<String>(StatusCode.Fail.getCode(),validate);
            }
            Boolean bool = menuService.save(menu);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }


    @ApiOperation(value = "根据id删除菜单信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "菜单ID", value = "要删除的菜单id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            menuService.delete(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新菜单信息")
    @PutMapping("/update")
    public Result<String> update(@RequestBody @ApiParam(name = "菜单对象", value = "传入json格式", required = true) Menu menu) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            String validate =validateForm(menu);
            if (StringUtils.isNotBlank(validate)){
                return new Result<String>(StatusCode.Fail.getCode(),validate);
            }
            menuService.updateById(menu);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "查询带 按钮的菜单")
    @GetMapping("/findNoButton")
    public Result<List<Map<String,Object>>> findNoButton(){
        Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>(StatusCode.Success);
        try{
            List<Map<String,Object>> menus =menuService.findNoButton();
            result.setData(menus);
            return result;
        }catch (Exception e){
            return new Result<List<Map<String,Object>>>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "查询带 按钮的菜单")
    @GetMapping("/findByRole/{role_id}")
    public Result<List<Map<String,Object>>> findByRole(@ApiParam(name = "角色ID", value = "查询的菜单对应的角色ID", required = true) @PathVariable Integer role_id){
        Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>(StatusCode.Success);
        try{
            List<Map<String,Object>> menus =menuService.findByRole(role_id);
            result.setData(menus);
            return result;
        }catch (Exception e){
            return new Result<List<Map<String,Object>>>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "")
    @GetMapping("/nav")
    public Result<List<Menu>> nav(){
        Result<String> result = new Result<String>(StatusCode.Success);

        return null;
    }







    //验证参数是否正确
    private String validateForm(Menu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            return "菜单名称不能为空";
        }
        if (menu.getParentId() == null) {
            return "上级菜单不能为空";
        }

        //菜单
        if (menu.getType() == ConstantUtils.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                return "菜单链接url不能为空";
            }
        }

        //上级菜单类型
        int parentType = ConstantUtils.MenuType.CATALOG.getValue();

        if (menu.getParentId() != 0) {
            Menu parentMenu = menuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == ConstantUtils.MenuType.CATALOG.getValue() || menu.getType() == ConstantUtils.MenuType.MENU.getValue()) {
            if (parentType != ConstantUtils.MenuType.CATALOG.getValue()) {
                return "上级菜单只能为目录类型";
            }
            return "";
        }

        //按钮
        if (menu.getType() == ConstantUtils.MenuType.BUTTON.getValue()) {
            if (parentType != ConstantUtils.MenuType.MENU.getValue()) {
                return "上级菜单只能为菜单类型";
            }
            return "";
        }

        return "";
    }

}

