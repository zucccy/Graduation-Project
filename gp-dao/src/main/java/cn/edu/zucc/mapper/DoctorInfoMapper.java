package cn.edu.zucc.mapper;

import cn.edu.zucc.po.DoctorInfo;
import cn.edu.zucc.vo.DoctorVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface DoctorInfoMapper extends Mapper<DoctorInfo> {

    List<DoctorVO> findDoctorList(@Param("officeId") Long officeId,
                                  @Param("id") Long id,
                                  @Param("hospitalId") Long hospitalId,
                                  @Param("doctorName") String doctorName);
}