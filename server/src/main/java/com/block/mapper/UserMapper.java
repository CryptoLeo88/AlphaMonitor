package com.block.mapper;

import com.block.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select * ,\n" +
            "       (select name from organization where id=user.organization_id) as organization_name\n" +
            "from user ${ew.customSqlSegment}")
    <E extends IPage<User>> E list(E page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}