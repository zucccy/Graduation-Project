package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 15.04.2021
 */
@Data

public class MyPatientVO {


    /**
     * 患者编号
     */
    @ApiModelProperty(value = "患者编号")
    private Long id;

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

    /**
     * 患者状态 0未选中，1选中
     */
    @ApiModelProperty(value = "患者状态")
    private boolean status = false;
}
