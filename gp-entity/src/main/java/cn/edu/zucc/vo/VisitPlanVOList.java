package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 19.05.2021
 */
@Data
public class VisitPlanVOList {

    @ApiModelProperty("医生出诊计划列表 上午")
    List<VisitPlanVO> visitPlanVOAmList;

    @ApiModelProperty("医生出诊计划列表 下午")
    List<VisitPlanVO> visitPlanVOPmList;

    @ApiModelProperty("医生出诊计划列表 夜诊")
    List<VisitPlanVO> visitPlanVOEveList;
}
