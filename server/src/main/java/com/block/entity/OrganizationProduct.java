package com.block.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 机构产品
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OrganizationProduct对象", description = "机构产品")
public class OrganizationProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "机构ID")
    private Integer organizationId;

    @ApiModelProperty(value = "机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty(value = "产品ID")
    private Integer productId;

    @ApiModelProperty(value = "产品")
    @TableField(exist = false)
    private String productKey;

}
