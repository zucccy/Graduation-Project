package cn.edu.zucc.mapper;

import cn.edu.zucc.po.UserRelPatient;
import cn.edu.zucc.po.UserRelPatientExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRelPatientMapper extends Mapper<UserRelPatient> {
    long countByExample(UserRelPatientExample example);

    int deleteByExample(UserRelPatientExample example);

    List<UserRelPatient> selectByExample(UserRelPatientExample example);

    int updateByExampleSelective(@Param("record") UserRelPatient record, @Param("example") UserRelPatientExample example);

    int updateByExample(@Param("record") UserRelPatient record, @Param("example") UserRelPatientExample example);
}