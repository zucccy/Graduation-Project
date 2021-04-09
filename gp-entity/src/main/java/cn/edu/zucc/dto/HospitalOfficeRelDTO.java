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
@ApiModel(value = "HospitalOfficeRelDTO", description = "医院科室关系参数")
@Data
public class HospitalOfficeRelDTO {

    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    private Long hospitalId;

    /**
     * 科室编号
     */
    @ApiModelProperty(value = "科室编号")
    private Long officeId;

}
