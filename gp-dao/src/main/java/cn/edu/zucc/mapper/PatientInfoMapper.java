package cn.edu.zucc.mapper;

import cn.edu.zucc.po.PatientInfo;
import cn.edu.zucc.po.PatientInfoExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PatientInfoMapper extends Mapper<PatientInfo> {
    long countByExample(PatientInfoExample example);

    int deleteByExample(PatientInfoExample example);

    List<PatientInfo> selectByExample(PatientInfoExample example);

    int updateByExampleSelective(@Param("record") PatientInfo record, @Param("example") PatientInfoExample example);

    int updateByExample(@Param("record") PatientInfo record, @Param("example") PatientInfoExample example);
}