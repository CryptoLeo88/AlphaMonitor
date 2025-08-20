package com.block.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.block.entity.RoleMenu;
import com.block.mapper.RoleMenuMapper;
import com.block.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
