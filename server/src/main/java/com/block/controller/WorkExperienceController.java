package com.block.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.WorkExperience;
import com.block.service.WorkExperienceService;
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
@Api("工作经历")
@RestController
@RequestMapping("/workExperience")
public class WorkExperienceController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final WorkExperienceService workExperienceService;

    @Autowired
    public WorkExperienceController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @ApiOperation(value = "查询所有工作经历信息page")
    @PostMapping("/findAll")

    public Result<IPage<WorkExperience>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
        Result<IPage<WorkExperience>> result = new Result<IPage<WorkExperience>>(StatusCode.Success);
        Map<String, String> search = pageUtil.getSearch();
        try {
            IPage<WorkExperience> workExperiences = workExperienceService.page(pageUtil.getPage(), pageUtil.getSize(),search);
            result.setData(workExperiences);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return new Result<IPage<WorkExperience>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有工作经历信息")
    @PostMapping("/list")

    public Result<List<WorkExperience>> list() {
        Result<List<WorkExperience>> result = new Result<List<WorkExperience>>(StatusCode.Success);
        try {
            List<WorkExperience> workExperiences = workExperienceService.list();
            result.setData(workExperiences);
            return result;
        } catch (Exception e) {
            return new Result<List<WorkExperience>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询工作经历表信息")
    @PostMapping("/findById/{id}")
    public Result<WorkExperience> findById(@PathVariable Integer id) {
        Result<WorkExperience> result = new Result<WorkExperience>(StatusCode.Success);
        try {
            WorkExperience workExperience = workExperienceService.getById(id);
            result.setData(workExperience);
        } catch (Exception e) {
            return new Result<WorkExperience>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加工作经历表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "工作经历表对象", value = "传入json格式", required = true) WorkExperience workExperience) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = workExperienceService.saveOrUpdate(workExperience);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除工作经历表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "工作经历表ID", value = "要删除的工作经历表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            workExperienceService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新工作经历表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "工作经历表对象", value = "传入json格式", required = true) WorkExperience workExperience) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = workExperienceService.updateById(workExperience);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

}

