package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@ApiModel(value = "VisitPatientInfoDTO", description = "患者信息参数")
@Data
public class VisitPatientInfoDTO {

    @ApiModelProperty(value = "预约编号")
    private Long appointmentId;

    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    @ApiModelProperty(value = "患者性别")
    private Byte sex;

    @ApiModelProperty(value = "患者出生日期")
    private Date birthday;

    @ApiModelProperty(value = "就诊时段")
    private Byte visitPeriod;

    @ApiModelProperty(value = "预约状态")
    private Byte visitStatus;
}
