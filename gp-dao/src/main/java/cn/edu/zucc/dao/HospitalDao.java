package cn.edu.zucc.dao;

import cn.edu.zucc.mapper.HospitalMapper;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.HospitalExample;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Description: 医院相关dao类，调用mapper接口
 *
 * @author chenyun
 * @since 08.04.2021
 */
@Repository
public class HospitalDao {

    @Resource
    private HospitalMapper hospitalMapper;

    public Hospital findHospitalById(Long id) {
        HospitalExample example = new HospitalExample();
        example.createCriteria().andIdEqualTo(id);
        return hospitalMapper.selectOneByExample(example);
    }
}
