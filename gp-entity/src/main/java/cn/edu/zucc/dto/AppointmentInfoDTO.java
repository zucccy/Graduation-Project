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
@ApiModel(value = "AppointmentInfoDTO", description = "预约信息参数")
@Data
public class AppointmentInfoDTO {

    /**
     * 医生编号
     */
    @ApiModelProperty(value = "医生编号")
    private Long doctorId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userId;

    /**
     * 患者编号
     */
    @ApiModelProperty(value = "患者编号")
    private Long patientId;

    /**
     * 出诊编号
     */
    @ApiModelProperty(value = "出诊编号")
    private Long visitId;

    /**
     * 预约状态 默认为1 1已预约，2已退号，3未按时就诊，过号，4预约中， 5预约结束
     */
    @ApiModelProperty(value = "预约状态")
    private Byte visitStatus;
}
