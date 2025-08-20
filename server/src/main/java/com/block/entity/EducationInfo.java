package com.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2023-04-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="EducationInfo对象", description="")
public class EducationInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer personId;

    private String schoolName;

    private String collegeName;

    private String majorName;

    private LocalDate admissionTime;

    private LocalDate graduationTime;

    private String degreeLevel;

    private Boolean isGraduated;

    private String authenticationNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
