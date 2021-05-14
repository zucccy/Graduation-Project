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
public class HospitalLocalationVO {

    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    private Long id;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    /**
     * 医院地址
     */
    @ApiModelProperty(value = "医院地址")
    private String address;

    /**
     * 医院地址经度
     */
    @ApiModelProperty(value = "医院地址经度")
    private BigDecimal longitudes;

    /**
     * 医院地址纬度
     */
    @ApiModelProperty(value = "医院地址纬度")
    private BigDecimal latitudes;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private Double distance;
}
