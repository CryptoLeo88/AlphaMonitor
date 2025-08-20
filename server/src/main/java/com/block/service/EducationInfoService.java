package com.block.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.EducationInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.block.entity.ProductType;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrfc
 * @since 2023-04-28
 */
public interface EducationInfoService extends IService<EducationInfo> {
    IPage<EducationInfo> page(Integer page, Integer size, Map<String, String> search);
}
