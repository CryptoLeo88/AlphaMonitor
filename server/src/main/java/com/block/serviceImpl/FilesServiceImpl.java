package com.block.serviceImpl;

import com.block.entity.Files;
import com.block.exception.CommonException;
import com.block.mapper.FilesMapper;
import com.block.service.FilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-10-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {

    @Value("${files.uploadFilePath}")
    private String uploadFilePath;

    @Value("${files.virtualUploadPath}")
    private String virtualUploadPath;


    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public Files upload(MultipartFile multipartFile) {
        String tempUploadFilePath = System.getProperty("user.home") + uploadFilePath;
        try {
            //获取文件名
            String originName = multipartFile.getOriginalFilename();
            //获取文件后缀
            assert originName != null;
            String suffixName = originName.substring(originName.lastIndexOf(".") + 1).toLowerCase();
            //创建唯一文件名
            String uuid = UUID.randomUUID().toString();
            String pictureName = uuid + "." + suffixName;
            File file = new File(tempUploadFilePath + pictureName);
            if (!file.getParentFile().exists()) {
                Boolean bool = file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            return new Files(originName, pictureName, virtualUploadPath + pictureName, LocalDateTime.now());
        } catch (IOException e) {
            throw new CommonException("文件上传失败!");
        }
    }
}
