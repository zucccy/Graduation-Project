package cn.edu.zucc.mapper;

import cn.edu.zucc.po.DoctorInfo;
import cn.edu.zucc.po.DoctorInfoExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DoctorInfoMapper extends Mapper<DoctorInfo> {
    long countByExample(DoctorInfoExample example);

    int deleteByExample(DoctorInfoExample example);

    List<DoctorInfo> selectByExample(DoctorInfoExample example);

    int updateByExampleSelective(@Param("record") DoctorInfo record, @Param("example") DoctorInfoExample example);

    int updateByExample(@Param("record") DoctorInfo record, @Param("example") DoctorInfoExample example);
}