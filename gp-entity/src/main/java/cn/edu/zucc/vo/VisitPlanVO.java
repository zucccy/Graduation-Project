package cn.edu.zucc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 16.04.2021
 */
@Data
public class VisitPlanVO {

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
    @JsonFormat(pattern = "MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "出诊日期")
    private Date visitDay;

    /**
     * 出诊具体时间
     */
    @ApiModelProperty(value = "出诊具体时间")
    private String timePeriod;

    /**
     * 出诊编号
     */
    @ApiModelProperty(value = "出诊编号")
    private Long id;
}
