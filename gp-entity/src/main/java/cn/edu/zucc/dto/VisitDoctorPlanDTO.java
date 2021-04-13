package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 13.04.2021
 */
@ApiModel(value = "VisitDoctorPlanDTO", description = "医生出诊计划DTO")
@Data
public class VisitDoctorPlanDTO {

    @ApiModelProperty("医生信息")
    private DoctorInfoDTO doctorInfoDTO;

    @ApiModelProperty("医生出诊计划列表")
    private List<VisitPlanInfoDTO> visitPlanInfoDTOList;
}
