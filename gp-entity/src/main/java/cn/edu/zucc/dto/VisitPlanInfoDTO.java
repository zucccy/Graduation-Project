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
@ApiModel(value = "VisitPlanInfoDTO", description = "出诊计划信息参数")
@Data
public class VisitPlanInfoDTO {

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
     * 医生编号
     */
    @ApiModelProperty(value = "医生编号")
    private Long doctorId;

    /**
     * 出诊类型 0普通门诊1专家门诊，默认0
     */
    @ApiModelProperty(value = "出诊类型 0普通门诊1专家门诊，默认0")
    private Byte visitType;

    /**
     * 出诊时段 1上午 2下午 3晚上，默认为1
     */
    @ApiModelProperty(value = "出诊时段 1上午 2下午 3晚上，默认为1")
    private Byte visitPeriod;

    /**
     * 出诊日期
     */
    @ApiModelProperty(value = "出诊日期")
    private Date visitDay;

    /**
     * 出诊具体时间
     */
    @ApiModelProperty(value = "出诊具体时间")
    private String timePeriod;
}
