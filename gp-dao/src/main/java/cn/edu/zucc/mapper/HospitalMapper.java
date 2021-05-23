package cn.edu.zucc.mapper;

import cn.edu.zucc.po.Hospital;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface HospitalMapper extends Mapper<Hospital> {
    List<Hospital> findHospitalList(@Param("hospitalName") String hospitalName);
}