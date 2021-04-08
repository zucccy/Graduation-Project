package cn.edu.zucc.mapper;

import cn.edu.zucc.po.HospitalRelOffice;
import cn.edu.zucc.po.HospitalRelOfficeExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HospitalRelOfficeMapper extends Mapper<HospitalRelOffice> {
    long countByExample(HospitalRelOfficeExample example);

    int deleteByExample(HospitalRelOfficeExample example);

    List<HospitalRelOffice> selectByExample(HospitalRelOfficeExample example);

    int updateByExampleSelective(@Param("record") HospitalRelOffice record, @Param("example") HospitalRelOfficeExample example);

    int updateByExample(@Param("record") HospitalRelOffice record, @Param("example") HospitalRelOfficeExample example);
}