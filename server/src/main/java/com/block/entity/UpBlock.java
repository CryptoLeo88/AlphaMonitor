package com.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lrfc
 * @since 2023-06-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="UpBlock对象", description="")
public class UpBlock implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String hashcode;

    private Integer realId;

    @TableField("up_Block_info")
    private String upBlockInfo;

    private Integer status;

    private String remark;


}
