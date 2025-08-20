package com.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Menu对象", description = "菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private Integer parentId;

    @ApiModelProperty(value = "父菜单名称")
    @TableField(exist = false)
    private String parentName;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "授权")
    private String perms;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "标题")
    private String title;


    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;


}
