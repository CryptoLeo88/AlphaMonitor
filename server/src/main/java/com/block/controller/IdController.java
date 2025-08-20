package com.block.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.EducationInfo;
import com.block.entity.Id;
import com.block.service.IdService;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2023-06-08
 */
@Api(tags = "获取id")
@RestController
@RequestMapping("/id")
public class IdController {

    @Autowired
    private IdService idService;

    @GetMapping("getId")
    public Result<Integer> getId(){
        Result<Integer> result = new Result<>(StatusCode.Success);
        Id byId = idService.getById("1");
        Integer num = byId.getNum();
        boolean update = idService.update(new Id(1, num+1),new QueryWrapper<>());
        result.setData(num);
        return result;
    }


}

