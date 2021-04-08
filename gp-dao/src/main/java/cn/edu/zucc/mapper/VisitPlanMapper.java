package cn.edu.zucc.mapper;

import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.po.VisitPlanExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VisitPlanMapper extends Mapper<VisitPlan> {
    long countByExample(VisitPlanExample example);

    int deleteByExample(VisitPlanExample example);

    List<VisitPlan> selectByExample(VisitPlanExample example);

    int updateByExampleSelective(@Param("record") VisitPlan record, @Param("example") VisitPlanExample example);

    int updateByExample(@Param("record") VisitPlan record, @Param("example") VisitPlanExample example);
}