package cn.edu.zucc.mapper;

import cn.edu.zucc.po.AppointmentInfo;
import cn.edu.zucc.po.AppointmentInfoExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AppointmentInfoMapper extends Mapper<AppointmentInfo> {
    long countByExample(AppointmentInfoExample example);

    int deleteByExample(AppointmentInfoExample example);

    List<AppointmentInfo> selectByExample(AppointmentInfoExample example);

    int updateByExampleSelective(@Param("record") AppointmentInfo record, @Param("example") AppointmentInfoExample example);

    int updateByExample(@Param("record") AppointmentInfo record, @Param("example") AppointmentInfoExample example);
}