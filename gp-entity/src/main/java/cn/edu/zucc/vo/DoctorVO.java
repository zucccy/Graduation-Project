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
public class DoctorVO {

    /**
     * 医生编号
     */
    @ApiModelProperty(value = "医生编号")
    private Long id;

    /**
     * 医生名字
     */
    @ApiModelProperty(value = "医生名字")
    private String doctorName;

    /**
     * 医生性别 默认为1 男1女0
     */
    @ApiModelProperty(value = "医生性别")
    private Byte sex;

    /**
     * 医生职位
     */
    @ApiModelProperty(value = "医生职位")
    private String position;

    /**
     * 医生描述
     */
    @ApiModelProperty(value = "医生描述")
    private String description;

    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    private String officeName;

    /**
     * 科室编号
     */
    @ApiModelProperty(value = "科室编号")
    private Long officeId;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    private Long hospitalId;

    /**
     * 医院图片
     */
    @ApiModelProperty(value = "医院图片")
    private String src;
}
