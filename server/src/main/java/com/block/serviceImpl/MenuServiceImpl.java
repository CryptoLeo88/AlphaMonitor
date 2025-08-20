package com.block.serviceImpl;

import com.block.entity.Menu;
import com.block.entity.RoleMenu;
import com.block.exception.CommonException;
import com.block.mapper.MenuMapper;
import com.block.mapper.RoleMenuMapper;
import com.block.service.MenuService;
import com.block.utils.ConstantUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final RoleMenuMapper roleMenuMapper;
    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(RoleMenuMapper roleMenuMapper, MenuMapper menuMapper) {
        this.roleMenuMapper = roleMenuMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public Boolean delete(Integer id) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_id", id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(wrapper);
        if (roleMenus.size() != 0) {
            throw new CommonException("删除失败,与表role_menu有关联！");
        } else {
            System.out.println("删除成功！");
            this.removeById(id);
        }
        return Boolean.TRUE;
    }

    @Override

    public IPage<Menu> page(Integer page, Integer size) {

        return menuMapper.list(new Page<>(page, size));
    }

    @Override

    public List<Map<String, Object>> findNoButton() {
        List<Menu> menus = menuMapper.findNoButton();
        Menu menu = new Menu();
        menu.setId(ConstantUtils.TOP_MENU_ID);
        menu.setName(ConstantUtils.TOP_MENU_NAME);
        menu.setParentId(-1);
        menus.add(menu);
        MenuTree menusTree = new MenuTree(menus);
        return menusTree.builTree();
    }

    @Override
    public List<Map<String, Object>> findByRole(Integer role_id) {
        List<Menu> menus = menuMapper.findByRole(role_id);
        Menu menu = new Menu();
        menu.setId(ConstantUtils.TOP_MENU_ID);
        menu.setName(ConstantUtils.TOP_MENU_NAME);
        menu.setParentId(-1);
        menus.add(menu);
        MenuTree menusTree = new MenuTree(menus);
        return menusTree.builTree();
    }
}

class MenuTree {
    private List<Menu> menuList = new ArrayList<Menu>();

    public MenuTree(List<Menu> menuList) {
        this.menuList = menuList;
    }

    //建立树形结构
    public List<Map<String, Object>> builTree() {
        List<Map<String, Object>> treeMenus = new ArrayList<>();
        for (Map<String, Object> menuNode : getRootNode()) {
            buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private Map<String, Object> buildChilTree(Map<String, Object> pNode) {
        List<Map<String, Object>> chilMenus = new ArrayList<>();
        for (Menu menuNode : menuList) {
            if (menuNode.getParentId().equals(pNode.get("value"))) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", menuNode.getId());
                map.put("component", menuNode.getComponent());
                map.put("icon", menuNode.getIcon());
                map.put("title", menuNode.getTitle());
                map.put("name", menuNode.getName());
                map.put("path", menuNode.getPath());
                map.put("url", menuNode.getUrl());
                map.put("pid", menuNode.getParentId());
                chilMenus.add(buildChilTree(map));
            }
        }
        if (!chilMenus.isEmpty()) {
            pNode.put("children", chilMenus);
        }
        return pNode;
    }

    //获取根节点
    private List<Map<String, Object>> getRootNode() {
        List<Map<String, Object>> rootMenuLists = new ArrayList<>();
        for (Menu menuNode : menuList) {
            if (menuNode.getParentId().equals(-1)) {
                Map<String, Object> map = new HashMap<>();
                map.put("value", menuNode.getId());
                map.put("label", menuNode.getName());
                rootMenuLists.add(map);
            }
        }
        return rootMenuLists;
    }
}
