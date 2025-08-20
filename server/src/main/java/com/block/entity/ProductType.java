package com.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lrfc
 * @since 2023-04-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ProductType对象", description="")
public class ProductType implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String productTypeName;

    private String remark;


}
