package com.block.mapper;

import com.block.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Component
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select m1.*,(select name from menu m2 where m1.parent_id=m2.id) as parent_name from menu m1")
    <E extends IPage<Menu>> E list(E page);

    @Select("select m1.*,(select name from menu m2 where m1.parent_id=m2.id) as parent_name from menu m1 where type !=2")
    List<Menu> findNoButton();

    @Select("select m.* from menu m join role_menu rm on m.id = rm.menu_id where rm.role_id= ${role_id}")
    List<Menu> findByRole( Integer role_id);

}
