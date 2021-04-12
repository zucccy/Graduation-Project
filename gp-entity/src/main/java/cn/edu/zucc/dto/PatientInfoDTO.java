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
@ApiModel(value = "PatientInfoDTO", description = "患者信息参数")
@Data
public class PatientInfoDTO {

    /**
     * 患者名称
     */
    @ApiModelProperty(value = "患者名称")
    private String patientName;

    /**
     * 患者手机号
     */
    @ApiModelProperty(value = "患者手机号")
    private String phone;

    /**
     * 患者身份证号
     */
    @ApiModelProperty(value = "患者身份证号")
    private String idCard;

    /**
     * 患者性别，1男0女，默认为1
     */
    @ApiModelProperty(value = "患者性别，1男0女，默认为1")
    private Byte sex;

    /**
     * 患者生日
     */
    @ApiModelProperty(value = "患者生日")
    private Date birthday;

    /**
     * 病情描述
     */
    @ApiModelProperty(value = "病情描述")
    private String description;
}
