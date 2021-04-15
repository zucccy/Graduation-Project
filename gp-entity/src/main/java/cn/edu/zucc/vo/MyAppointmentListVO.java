package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 15.04.2021
 */
@Data
public class MyAppointmentListVO {

    /**
     * 预约编号
     */
    @ApiModelProperty(value = "预约编号")
    private Long id;

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
     * 出诊具体时间
     */
    @ApiModelProperty(value = "出诊具体时间")
    private String timePeriod;
}
