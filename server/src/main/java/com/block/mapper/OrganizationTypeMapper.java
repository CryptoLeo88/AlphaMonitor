package com.block.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.block.entity.OrganizationType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构类型 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Component
public interface OrganizationTypeMapper extends BaseMapper<OrganizationType> {
    @Select("select id,name from organization_type")
    List<Map<String, Object>> findIdAndName();
}
