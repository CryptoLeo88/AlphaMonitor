package com.block.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.block.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface RoleService extends IService<Role> {
    //判断role与其他表是否有关联
    Boolean delete(Integer id);

    List<Map<String, Object>> findIdAndName();

    IPage<Role> page(Integer page, Integer size, Map<String, String> search);
}
