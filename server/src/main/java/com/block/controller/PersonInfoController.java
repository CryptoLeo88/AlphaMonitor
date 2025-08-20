package com.block.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.PersonInfo;
import com.block.service.PersonInfoService;
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
@Api(tags = "个人信息")
@RestController
@RequestMapping("/personInfo")
public class PersonInfoController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final PersonInfoService personInfoService;

    @Autowired
    public PersonInfoController(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    @ApiOperation(value = "查询所有个人信息信息page")
    @PostMapping("/findAll")

    public Result<IPage<PersonInfo>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<PersonInfo>> result = new Result<IPage<PersonInfo>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<PersonInfo> personInfos = personInfoService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(personInfos);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return new Result<IPage<PersonInfo>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有个人信息信息")
    @PostMapping("/list")
    public Result<List<PersonInfo>> list() {
        Result<List<PersonInfo>> result = new Result<List<PersonInfo>>(StatusCode.Success);
        try {
            List<PersonInfo> personInfos = personInfoService.list();
            result.setData(personInfos);
            return result;
        } catch (Exception e) {
            return new Result<List<PersonInfo>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询个人信息表信息")
    @PostMapping("/findById/{id}")
    public Result<PersonInfo> findById(@PathVariable Integer id) {
        Result<PersonInfo> result = new Result<PersonInfo>(StatusCode.Success);
        try {
            PersonInfo personInfo = personInfoService.getById(id);
            result.setData(personInfo);
        } catch (Exception e) {
            return new Result<PersonInfo>(StatusCode.Fail);
        }
        return result;
    }

    @ApiOperation(value = "根据user_id查询个人信息表信息")
    @PostMapping("/findByUserId/{userId}")
    public Result<PersonInfo> findByUserId(@PathVariable Integer userId) {
        Result<PersonInfo> result = new Result<PersonInfo>(StatusCode.Success);
        try {
            QueryWrapper<PersonInfo> personInfoQueryWrapper = new QueryWrapper<>();
            personInfoQueryWrapper.eq("user_id",userId);
            PersonInfo personInfo = personInfoService.getOne(personInfoQueryWrapper);
            result.setData(personInfo);
        } catch (Exception e) {
            return new Result<PersonInfo>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加个人信息表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "个人信息表对象", value = "传入json格式", required = true) PersonInfo personInfo) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = personInfoService.saveOrUpdate(personInfo);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除个人信息表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "个人信息表ID", value = "要删除的个人信息表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            personInfoService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新个人信息表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "个人信息表对象", value = "传入json格式", required = true) PersonInfo personInfo) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = personInfoService.updateById(personInfo);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

}

