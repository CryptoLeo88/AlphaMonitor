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
 * 机构表
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Organization对象", description = "机构表")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "机构名称")
    private String name;

    @ApiModelProperty(value = "机构简称")
    private String simplify;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "所在行政区域")
    private String administrativeArea;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "机构类型ID")
    private Integer organizationTypeId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "机构类型")
    @TableField(exist = false)
    private String organizationType;

}
