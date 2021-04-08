package cn.edu.zucc.mapper;

import cn.edu.zucc.po.Office;
import cn.edu.zucc.po.OfficeExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OfficeMapper extends Mapper<Office> {
    long countByExample(OfficeExample example);

    int deleteByExample(OfficeExample example);

    List<Office> selectByExample(OfficeExample example);

    int updateByExampleSelective(@Param("record") Office record, @Param("example") OfficeExample example);

    int updateByExample(@Param("record") Office record, @Param("example") OfficeExample example);
}