package com.block.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.EducationInfo;
import com.block.service.EducationInfoService;
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
@Api("教育信息")
@RestController
@RequestMapping("/educationInfo")
public class EducationInfoController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final EducationInfoService educationInfoService;

    @Autowired
    public EducationInfoController(EducationInfoService educationInfoService) {
        this.educationInfoService = educationInfoService;
    }

    @ApiOperation(value = "查询所有教育信息信息page")
    @PostMapping("/findAll")

    public Result<IPage<EducationInfo>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<EducationInfo>> result = new Result<IPage<EducationInfo>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<EducationInfo> educationInfos = educationInfoService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(educationInfos);
            return result;
        } catch (Exception e) {
            return new Result<IPage<EducationInfo>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有教育信息信息")
    @PostMapping("/list")

    public Result<List<EducationInfo>> list() {
        Result<List<EducationInfo>> result = new Result<List<EducationInfo>>(StatusCode.Success);
        try {
            List<EducationInfo> educationInfos = educationInfoService.list();
            result.setData(educationInfos);
            return result;
        } catch (Exception e) {
            return new Result<List<EducationInfo>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询教育信息表信息")
    @PostMapping("/findById/{id}")
    public Result<EducationInfo> findById(@PathVariable Integer id) {
        Result<EducationInfo> result = new Result<EducationInfo>(StatusCode.Success);
        try {
            EducationInfo educationInfo = educationInfoService.getById(id);
            result.setData(educationInfo);
        } catch (Exception e) {
            return new Result<EducationInfo>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加教育信息表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "教育信息表对象", value = "传入json格式", required = true) EducationInfo educationInfo) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = educationInfoService.saveOrUpdate(educationInfo);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除教育信息表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "教育信息表ID", value = "要删除的教育信息表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            educationInfoService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新教育信息表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "教育信息表对象", value = "传入json格式", required = true) EducationInfo educationInfo) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = educationInfoService.updateById(educationInfo);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }
}

