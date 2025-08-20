package com.block.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.block.entity.OrganizationProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 机构产品 Mapper 接口
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Component
public interface OrganizationProductMapper extends BaseMapper<OrganizationProduct> {
    @Select("select op.*,og.name as organizationName,pr.product_key as productKey from organization_product op left join organization og on op.organization_id = og.id left join product pr on op.product_id = pr.id ${ew.customSqlSegment}")
    <E extends IPage<OrganizationProduct>> E list(E page, @Param(Constants.WRAPPER) Wrapper<OrganizationProduct> queryWrapper);
}
