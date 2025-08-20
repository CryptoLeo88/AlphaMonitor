package com.block.controller;


import com.block.entity.Files;
import com.block.service.FilesService;
import com.block.utils.Result;
import com.block.utils.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lrfc
 * @since 2020-10-17
 */
@Api(tags = "文件上管理")
@RestController
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }


    @ApiOperation(value = "保存文件")
    @PostMapping("/save")
    public Result<List<Files>> saveRecord(@RequestParam("file") MultipartFile[] multipartFiles) {
        Result<List<Files>> result = new Result<List<Files>>(StatusCode.Success);
        List<Files> fileses = new ArrayList<>();
        try {
            if (multipartFiles.length != 0 && !multipartFiles[0].isEmpty()) {
                for (MultipartFile multipartFile : multipartFiles) {
                    Files files = filesService.upload(multipartFile);
                    filesService.save(files);
                    fileses.add(files);
                }
            }
            result.setData(fileses);
            return result;
        } catch (Exception e) {
            return new Result<List<Files>>(StatusCode.Fail.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "删除文件")
    @PostMapping("/delete_file")
    @ApiImplicitParam(name = "filepath", required = true, value = "删除的单个文件的路径", dataTypeClass = String.class, paramType = "query")
    public Result<Boolean> deleteFile(String filepath) {
        Result<Boolean> result = new Result<Boolean>(StatusCode.Success);
        String realPath = filepath.replace("/file/", "/root/File/");
        try {
            File file = new File(realPath);
            if (file.delete()) {
                result.setData(Boolean.TRUE);
                return result;
            } else {
                result.setData(Boolean.FALSE);
            }
        } catch (Exception e) {
            return new Result<Boolean>(StatusCode.Fail.getCode(), e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "保存单个文件")
    @PostMapping("/save_file")
    public Result<String> saveFile(@RequestParam("file") MultipartFile multipartFile) {
        Result<String> result = new Result<String>(StatusCode.Success);
        try {
            if(!multipartFile.isEmpty()){
                Files files = filesService.upload(multipartFile);
                result.setData(files.getPicturePath());
            }
            return result;
        } catch (Exception e) {
            return new Result<String>(StatusCode.Fail.getCode(), e.getMessage());
        }

    }
}

