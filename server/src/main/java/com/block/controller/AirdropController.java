package com.block.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.Airdrop;
import com.block.service.AirdropService;
import com.block.serviceImpl.DexscreenerService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import com.block.vo.AirDropVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2025-07-14
 */
@RestController
@RequestMapping("/airdrop")
public class AirdropController {
        private final Logger LOGGER = LoggerFactory.getLogger(getClass());


        private final AirdropService airdropService;

        private final DexscreenerService dexscreenerService;

        @Autowired
        public AirdropController(AirdropService airdropService,DexscreenerService dexscreenerService) {
            this.airdropService = airdropService;
            this.dexscreenerService = dexscreenerService;
        }

        @ApiOperation(value = "查询所有家庭成员信息page")
        @PostMapping("/findAll")
        public Result<IPage<Airdrop>> findAll(@RequestBody @ApiParam(name = "分页及搜索关键字", value = "传入json格式", required = true) PageUtil pageUtil) {
            Result<IPage<Airdrop>> result = new Result<IPage<Airdrop>>(StatusCode.Success);
            Map<String, String> search = pageUtil.getSearch();

            try {
                IPage<Airdrop> airdrops = airdropService.page(pageUtil.getPage(), pageUtil.getSize(),search);
                result.setData(airdrops);
                return result;
            } catch (Exception e) {
                return new Result<IPage<Airdrop>>(StatusCode.Fail.getCode(),e.getMessage());
            }

        }

        @ApiOperation(value = "查询所有家庭成员信息")
        @PostMapping("/list")

        public Result<List<Airdrop>> list() {
            Result<List<Airdrop>> result = new Result<>(StatusCode.Success);
            try {
                List<Airdrop> airdrops = airdropService.list();
                result.setData(airdrops);
                return result;

            } catch (Exception e) {
                return new Result<>(StatusCode.Fail.getCode(), e.getMessage());
            }
        }


        @ApiOperation(value = "根据id查询空投信息")
        @PostMapping("/findById/{id}")
        public Result<Airdrop> findById(@PathVariable Integer id) {
            Result<Airdrop> result = new Result<Airdrop>(StatusCode.Success);
            try {
                Airdrop airdrop = airdropService.getById(id);
                result.setData(airdrop);
            } catch (Exception e) {
                return new Result<Airdrop>(StatusCode.Fail);
            }
            return result;
        }


        @ApiOperation(value = "添加空投信息")
        @PostMapping("/add")
        public Result<Boolean> add(@RequestBody @ApiParam(name = "空投对象", value = "传入json格式", required = true) Airdrop airdrop) {
            Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
            try {
                Boolean bool = airdropService.saveOrUpdate(airdrop);
                result.setData(bool);
                return result;
            } catch (Exception e) {
                return new Result<Boolean>(StatusCode.Fail);
            }
        }


        @ApiOperation(value = "根据id删除空投信息")
        @DeleteMapping("/delete/{id}")
        public Result<String> delete(@ApiParam(name = "空投ID", value = "要删除的空投id的值", required = true) @PathVariable Integer id) {
            Result<String> result = new Result<String>(StatusCode.Success);
            try {
                airdropService.removeById(id);
                return result;
            } catch (Exception e) {
                return new Result<String>(StatusCode.Fail);
            }

        }

        @ApiOperation(value = "更新空投信息")
        @PutMapping("/update")
        public Result<Boolean> update(@RequestBody @ApiParam(name = "空投对象", value = "传入json格式", required = true) Airdrop airdrop) {
            Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
            try {
                Boolean bool = airdropService.updateById(airdrop);
                result.setData(bool);
                return result;
            } catch (Exception e) {
                return new Result<Boolean>(StatusCode.Fail);
            }
        }



    }




