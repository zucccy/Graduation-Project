package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Description: 科室信息DTO
 *
 * @author chenyun
 * @since 09.04.2021
 */
@ApiModel(value = "OfficeInfoDTO", description = "科室信息参数")
@Data
public class OfficeInfoDTO {

    @ApiModelProperty(value = "科室编号")
    private Long id;

    /**
     * 父级科室编号，从1开始，顶级为0
     */
    @ApiModelProperty(value = "父级科室编号")
    private Long parentId;

    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    private String officeName;

    /**
     * 科室介绍
     */
    @ApiModelProperty(value = "科室介绍")
    private String description;
}
