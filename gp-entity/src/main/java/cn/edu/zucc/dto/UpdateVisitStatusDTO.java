package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 19.05.2021
 */
@ApiModel(value = "UpdateVisitStatusDTO", description = "修改预约状态参数")
@Data
public class UpdateVisitStatusDTO {

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
}
