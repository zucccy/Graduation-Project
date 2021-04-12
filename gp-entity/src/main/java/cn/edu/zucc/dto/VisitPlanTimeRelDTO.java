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
@ApiModel(value = "VisitPlanTimeRelDTO", description = "出诊计划时间关系参数")
@Data
public class VisitPlanTimeRelDTO {

    /**
     * 出诊编号
     */
    @ApiModelProperty(value = "出诊编号")
    private Long visitId;

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
