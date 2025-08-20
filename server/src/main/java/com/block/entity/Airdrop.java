package com.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2025-07-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Airdrop对象", description="")
public class Airdrop implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "项目名称")
      private String name;

      @ApiModelProperty(value = "代币名称")
      private String tokenName;

      @ApiModelProperty(value = "分发数量")
      private Long amount;

      @ApiModelProperty(value = "备注")
      private String remark;

      @ApiModelProperty(value = "空头时间")
      private LocalDateTime airdropTime;

      @ApiModelProperty(value = "积分要求")
      private Integer score;

      @ApiModelProperty(value = "项目类型")
      private String type;

  @ApiModelProperty(value = "项目地址")
  private String address;
  @ApiModelProperty(value = "项目所在链")
  private String chain;


}
