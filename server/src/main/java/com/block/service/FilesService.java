package com.block.service;

import com.block.entity.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-10-17
 */
public interface FilesService extends IService<Files> {

    Files upload(MultipartFile multipartFile);
}
