package com.block.service;

import com.block.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface UserRoleService extends IService<UserRole> {

    //根据用户id查询该用户下的所有角色
    public List<String> getRoleById(Integer id);

    //根据用户id查询该用户下的所有角色
    public List<String> getRoleNameById(Integer id);

    void saveOrUpdate(Integer id, List<Integer> roleIdList);
}
