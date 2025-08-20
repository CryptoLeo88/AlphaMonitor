package com.block.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.FamilyMembers;
import com.block.entity.WorkExperience;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrfc
 * @since 2023-04-28
 */
public interface WorkExperienceService extends IService<WorkExperience> {
    IPage<WorkExperience> page(Integer page, Integer size, Map<String, String> search);

}
