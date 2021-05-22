package cn.edu.zucc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
@Data
public class MyAppointmentVO {

    /**
     * 预约状态 默认为1 1已预约，2已退号，3未按时就诊，过号，4预约中， 5预约结束
     */
    @ApiModelProperty(value = "预约状态")
    private Byte visitStatus;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    private Long hospitalId;

    /**
     * 医院地址
     */
    @ApiModelProperty(value = "医院地址")
    private String address;

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
     * 医生名字
     */
    @ApiModelProperty(value = "医生名字")
    private String doctorName;

    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    private String officeName;

    /**
     * 科室编号
     */
    @ApiModelProperty(value = "科室编号")
    private Long officeId;

    /**
     * 出诊具体时间
     */
    @ApiModelProperty(value = "出诊具体时间")
    private String timePeriod;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
