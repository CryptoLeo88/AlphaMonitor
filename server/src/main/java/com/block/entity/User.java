package com.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author lrfc
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "用户信息")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    //@TableField：字段属性不为数据库表字段，但又是必须使用的
    @ApiModelProperty(value = "该用户拥有的角色名称")
    @TableField(exist=false)
    private List<String> roles;

    @ApiModelProperty(value = "该用户拥有的角色ID")
    @TableField(exist=false)
    private List<Integer> roleIdList;

    @ApiModelProperty(value = "所属机构ID")
    private Integer organizationId;

    @ApiModelProperty(value = "所属机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
