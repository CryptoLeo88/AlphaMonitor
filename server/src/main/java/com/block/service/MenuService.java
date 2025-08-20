package com.block.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.block.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface MenuService extends IService<Menu> {
    //判断menu与其他表是否有关联
    Boolean delete(Integer id);

    IPage<Menu> page(Integer page, Integer size);

    List<Map<String,Object>> findNoButton();

    List<Map<String, Object>> findByRole(Integer role_id);
}
