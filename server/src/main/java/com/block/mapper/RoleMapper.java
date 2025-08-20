package com.block.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.block.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id,remark from role")
    List<Map<String, Object>> findIdAndName();

    @Select("select * from role ${ew.customSqlSegment}")
    <E extends IPage<Role>> E list(E page, @Param(Constants.WRAPPER) Wrapper<Role> queryWrapper);
}
