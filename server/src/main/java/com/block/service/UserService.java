package com.block.service;

import com.block.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
public interface UserService extends IService<User> {

    Boolean add(User user);

    Object findOne(QueryWrapper<User> wrapper);

    //判断user与其他表是否有关联
    Boolean delete(Integer id);

    IPage<User> page(Integer page, Integer size, Map<String, String> search);



}
