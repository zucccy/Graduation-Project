package cn.edu.zucc.mapper;

import cn.edu.zucc.po.Account;
import cn.edu.zucc.po.AccountExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AccountMapper extends Mapper<Account> {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    List<Account> selectByExample(AccountExample example);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);
}