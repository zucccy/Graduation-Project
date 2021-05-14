package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.05.2021
 */
@Data
public class AddressVO {

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private BigDecimal longitudes;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitudes;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private Double distance;
}
