package com.block.service;

import com.block.entity.OrganizationType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构类型 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface OrganizationTypeService extends IService<OrganizationType> {
    //判断organization_type与其他表是否有关联
    Boolean delete(Integer id);

    List<Map<String, Object>> findIdAndName();
}
