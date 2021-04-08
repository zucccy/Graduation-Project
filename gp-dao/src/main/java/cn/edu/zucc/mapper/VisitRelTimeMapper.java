package cn.edu.zucc.mapper;

import cn.edu.zucc.po.VisitRelTime;
import cn.edu.zucc.po.VisitRelTimeExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VisitRelTimeMapper extends Mapper<VisitRelTime> {
    long countByExample(VisitRelTimeExample example);

    int deleteByExample(VisitRelTimeExample example);

    List<VisitRelTime> selectByExample(VisitRelTimeExample example);

    int updateByExampleSelective(@Param("record") VisitRelTime record, @Param("example") VisitRelTimeExample example);

    int updateByExample(@Param("record") VisitRelTime record, @Param("example") VisitRelTimeExample example);
}