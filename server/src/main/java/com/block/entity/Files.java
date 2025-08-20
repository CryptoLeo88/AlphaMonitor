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
 * @since 2020-10-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Files对象", description="")
public class Files implements Serializable {

    private static final long serialVersionUID=1L;

  public Files(String originName, String pictureName, String picturePath,LocalDateTime date) {
    this.originName = originName;
    this.pictureName = pictureName;
    this.picturePath = picturePath;
    this.date= date;
  }

  public Files(){

  }

  @ApiModelProperty(value = "ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "图片初始名称")
      private String originName;

      @ApiModelProperty(value = "图片名称")
      private String pictureName;

      @ApiModelProperty(value = "图片路径")
      private String picturePath;

      @ApiModelProperty(value = "图片上传日期")
      private LocalDateTime date;


}
