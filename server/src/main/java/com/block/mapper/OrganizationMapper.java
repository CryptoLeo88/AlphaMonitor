package com.block.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.block.entity.Organization;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构表 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Component
public interface OrganizationMapper extends BaseMapper<Organization> {
    @Select("select id,name from organization")
    List<Map<String, Object>> findIdAndName();

    @Select("select a.* from (\n" +
            "                    select org.*,ot.name as organizationType\n" +
            "                    from organization org left join organization_type ot on org.organization_type_id = ot.id\n" +
            "                ) a ${ew.customSqlSegment}")
    <E extends IPage<Organization>> E list(E page, @Param(Constants.WRAPPER) Wrapper<Organization> queryWrapper);
}
