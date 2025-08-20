package com.block.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.UpBlock;
import com.block.service.UpBlockService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
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
 * @since 2023-06-08
 */
@RestController
@RequestMapping("/upBlock")
public class UpBlockController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final UpBlockService upBlockService;

    @Autowired
    public UpBlockController(UpBlockService upBlockService) {
        this.upBlockService = upBlockService;
    }



    @ApiOperation(value = "查询所有上链信息信息")
    @PostMapping("/list")

    public Result<List<UpBlock>> list() {
        Result<List<UpBlock>> result = new Result<List<UpBlock>>(StatusCode.Success);
        try {
            List<UpBlock> upBlocks = upBlockService.list();
            result.setData(upBlocks);
            return result;
        } catch (Exception e) {
            return new Result<List<UpBlock>>(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    @ApiOperation(value = "根据id查询上链信息表信息")
    @PostMapping("/findById/{id}")
    public Result<UpBlock> findById(@PathVariable Integer id) {
        Result<UpBlock> result = new Result<UpBlock>(StatusCode.Success);
        try {
            UpBlock upBlock = upBlockService.getById(id);
            result.setData(upBlock);
        } catch (Exception e) {
            return new Result<UpBlock>(StatusCode.Fail);
        }
        return result;
    }

    @ApiOperation(value = "根据id查询上链信息表信息")
    @PostMapping("/findByRealId/{id}")
    public Result<UpBlock> findByRealId(@PathVariable Integer id) {
        Result<UpBlock> result = new Result<UpBlock>(StatusCode.Success);
        try {
            UpBlock upBlock = upBlockService.getOne(new QueryWrapper<UpBlock>().eq("real_id",id));
            result.setData(upBlock);
        } catch (Exception e) {
            return new Result<UpBlock>(StatusCode.Fail);
        }
        return result;
    }


    @ApiOperation(value = "添加上链信息表信息")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @ApiParam(name = "上链信息表对象", value = "传入json格式", required = true) UpBlock upBlock) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = upBlockService.saveOrUpdate(upBlock);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }


    @ApiOperation(value = "根据id删除上链信息表信息")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@ApiParam(name = "上链信息表ID", value = "要删除的上链信息表id的值", required = true) @PathVariable Integer id) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            upBlockService.removeById(id);
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail);
        }

    }

    @ApiOperation(value = "更新上链信息表信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @ApiParam(name = "上链信息表对象", value = "传入json格式", required = true) UpBlock upBlock) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        try {
            Boolean bool = upBlockService.updateById(upBlock);
            result.setData(bool);
            return result;
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail);
        }
    }

}

