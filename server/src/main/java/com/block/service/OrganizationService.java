package com.block.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.block.entity.Organization;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface OrganizationService extends IService<Organization> {
    //判断表organization与其他表是否有关联
    Boolean delete(Integer id);

    List<Map<String, Object>> findIdAndName();

    IPage<Organization> page(Integer page, Integer size, Map<String, String> search);

    IPage<Organization> page1(Integer page, Integer size, Map<String, String> search);
}
