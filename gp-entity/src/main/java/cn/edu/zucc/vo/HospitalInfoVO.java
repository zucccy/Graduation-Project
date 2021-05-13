package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 13.05.2021
 */
@Data
public class HospitalInfoVO {

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
     * 医生列表
     */
    @ApiModelProperty(value = "医生列表")
    private List<DoctorVO> doctorVOList;

    /**
     * 科室列表
     */
    @ApiModelProperty(value = "科室列表")
    private List<OfficeVO> officeVOList;
}
