package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 16.04.2021
 */
@Data
public class VisitDoctorPlanVO {

    @ApiModelProperty("医生信息")
    DoctorVO doctorVO;

    @ApiModelProperty("医生出诊计划列表")
    List<VisitPlanVO> visitPlanVOList;
}