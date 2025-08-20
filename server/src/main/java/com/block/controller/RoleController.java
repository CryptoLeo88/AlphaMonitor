package com.block.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.Role;
import com.block.service.RoleService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Api(tags = "角色信息")
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "查询所有角色信息")
    @PostMapping("/findAll")

    public Result<IPage<Role>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<Role>> result = new Result<IPage<Role>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<Role> roles = roleService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(roles);
            return result;
        } catch (Exception e) {
            return new Result<IPage<Role>>(StatusCode.Fail);
        }

    }


    @ApiOperation(value = "根据id查询角色信息")
    @PostMapping("/findById/{id}")
    public Result<Role> findById(@PathVariable Integer id) {
        Result<Role> result = new Result<Role>(StatusCode.Success);
        try {
            Role role = roleService.getById(id);
            result.setData(role);
        } catch (Exception e) {
            return new Result<Role>(StatusCode.Fail);
        }
        return result;
    }

    @ApiOperation(value = "根据多个id查询角色信息")
    @PostMapping("/findByIds/{ids}")
    public Result<List<Role>> listByIds(@PathVariable List<Integer> ids) {
        Result<List<Role>> result = new Result<List<Role>>(StatusCode.Success);
        try {
            List<Role> roles = roleService.listByIds(ids);
            result.setData(roles);
        } catch (Exception e) {
            return new Result<List<Role>>(StatusCode.Fail);
        }
        return result;
    }



    @ApiOperation(value = "添加角色信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "角色对象", value = "传入json格式", required = true) Role role) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            role.setCreateTime(LocalDateTime.now());
            Boolean bool = roleService.saveOrUpdate(role);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除角色信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "角色ID", value = "要删除的角色id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            roleService.delete(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新角色信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "角色对象", value = "传入json格式", required = true) Role role) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            role.setCreateTime(LocalDateTime.now());
            Boolean bool = roleService.updateById(role);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

    @ApiOperation(value = "查询角色的id和角色名称信息")
    @PostMapping("/findIdAndName")
    public Result<List<Map<String, Object>>> findIdAndName() {
        Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>(StatusCode.Success);
        try {
            List<Map<String, Object>> lists = roleService.findIdAndName();
            result.setData(lists);
            return result;
        } catch (Exception e) {
            return new Result<List<Map<String, Object>>>(StatusCode.Fail.getCode(),e.getMessage());
        }
    }
}

