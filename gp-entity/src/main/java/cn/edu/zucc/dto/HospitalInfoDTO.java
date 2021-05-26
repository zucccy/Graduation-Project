package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * Description: TODO
 *
 * @author chenyun
 * @since 09.04.2021
 */
@ApiModel(value = "HospitalInfoDTO", description = "医院信息参数")
@Data
public class HospitalInfoDTO {

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
     * 医院电话，可以有多个
     */
    @ApiModelProperty(value = "医院电话")
    private String phone;

    /**
     * 医院描述
     */
    @ApiModelProperty(value = "医院描述")
    private String description;

    /**
     * 医院图片
     */
    @ApiModelProperty(value = "医院图片")
    private String src;
}
