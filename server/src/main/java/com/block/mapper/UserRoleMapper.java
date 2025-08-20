package com.block.mapper;

import com.block.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("select role_name from role left join user_role on role_id = role.id where user_id= #{uid}")
    List<String> getRoleById(Integer uid);

    @Select("select remark from role left join user_role on role_id = role.id where user_id= #{uid}")
    List<String> getRoleNameById(Integer uid);

    void saveOrUpdate(Integer id, List<Integer> roleIdList);
}
