package cn.edu.zucc.impl;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.dao.HospitalDao;
import cn.edu.zucc.po.Hospital;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description: 医院相关接口实现类
 *
 * @author chenyun
 * @since 08.04.2021
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalDao hospitalDao;

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDao.findHospitalById(id);
    }
}
