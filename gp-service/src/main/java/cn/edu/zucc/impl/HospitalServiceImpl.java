package cn.edu.zucc.impl;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.dao.HospitalDao;
import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.dto.HospitalOfficeRelDTO;
import cn.edu.zucc.po.Hospital;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Hospital> findHospitalList(String hospitalName, Integer pageNum, Integer pageSize) {
        return hospitalDao.findHospitalList(hospitalName, pageNum, pageSize);
    }

    @Override
    public boolean insert(HospitalInfoDTO hospitalInfoDTO) {
        return false;
    }

    @Override
    public boolean update(Long id, HospitalInfoDTO hospitalInfoDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean count(Long id) {
        return false;
    }

    @Override
    public boolean insertOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO) {
        return false;
    }

    @Override
    public boolean deleteOfficeRelation(Long hospitalId, Long officeId) {
        return false;
    }

    @Override
    public boolean countOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO) {
        return false;
    }
}
