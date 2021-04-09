package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 09.04.2021
 */
@ApiModel(value = "DoctorInfoDTO", description = "医生信息参数")
@Data
public class DoctorInfoDTO {

    /**
     * 医生名字
     */
    @ApiModelProperty(value = "医生名字")
    private String doctorName;

    /**
     * 医生性别 默认为1 男1女0
     */
    @ApiModelProperty(value = "医生性别")
    private Byte sex;

    /**
     * 医生职位
     */
    @ApiModelProperty(value = "医生职位")
    private String position;

    /**
     * 所属医院
     */
    @ApiModelProperty(value = "所属医院")
    private Long hospitalId;

    /**
     * 所属科室
     */
    @ApiModelProperty(value = "所属科室")
    private Long officeId;

    /**
     * 医生描述
     */
    @ApiModelProperty(value = "医生描述")
    private String description;
}
