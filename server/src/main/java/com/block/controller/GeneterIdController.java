package com.block.controller;

import com.block.entity.EducationInfo;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户登录")
@RestController
public class GeneterIdController {

//    @ApiOperation(value = "查询所有教育信息信息")
//    @PostMapping("/list")
//
//    public Result<Interger> list() {
//        Result<List<EducationInfo>> result = new Result<List<EducationInfo>>(StatusCode.Success);
//        try {
//            List<EducationInfo> educationInfos = educationInfoService.list();
//            result.setData(educationInfos);
//            return result;
//        } catch (Exception e) {
//            return new Result<List<EducationInfo>>(StatusCode.Fail.getCode(),e.getMessage());
//        }
//
//    }
}
