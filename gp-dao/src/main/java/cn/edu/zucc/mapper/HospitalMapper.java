package cn.edu.zucc.mapper;

import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.HospitalExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HospitalMapper extends Mapper<Hospital> {
    long countByExample(HospitalExample example);

    int deleteByExample(HospitalExample example);

    List<Hospital> selectByExample(HospitalExample example);

    int updateByExampleSelective(@Param("record") Hospital record, @Param("example") HospitalExample example);

    int updateByExample(@Param("record") Hospital record, @Param("example") HospitalExample example);
}