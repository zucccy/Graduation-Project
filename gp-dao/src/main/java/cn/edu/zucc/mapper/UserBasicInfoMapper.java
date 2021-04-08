package cn.edu.zucc.mapper;

import cn.edu.zucc.po.UserBasicInfo;
import cn.edu.zucc.po.UserBasicInfoExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserBasicInfoMapper extends Mapper<UserBasicInfo> {
    long countByExample(UserBasicInfoExample example);

    int deleteByExample(UserBasicInfoExample example);

    List<UserBasicInfo> selectByExample(UserBasicInfoExample example);

    int updateByExampleSelective(@Param("record") UserBasicInfo record, @Param("example") UserBasicInfoExample example);

    int updateByExample(@Param("record") UserBasicInfo record, @Param("example") UserBasicInfoExample example);
}