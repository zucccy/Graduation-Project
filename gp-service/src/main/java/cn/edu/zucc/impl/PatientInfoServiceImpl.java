package cn.edu.zucc.impl;

import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.dto.PatientInfoDTO;
import cn.edu.zucc.dto.PatientInfoUpadateDTO;
import cn.edu.zucc.po.PatientInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class PatientInfoServiceImpl implements PatientInfoService {
    @Override
    public boolean insert(Long userId, PatientInfoDTO patientInfoDTO) {
        return false;
    }

    @Override
    public boolean update(Long relationId, PatientInfoUpadateDTO patientInfoUpadateDTO) {
        return false;
    }

    @Override
    public boolean delete(Long relationId) {
        return false;
    }

    @Override
    public PatientInfo findPatientInfoById(Long id) {
        return null;
    }

    @Override
    public List<PatientInfo> findPatientList(String patientName, String phone, Byte sex, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<PatientInfo> findPatientList(Long userId) {
        return null;
    }

    @Override
    public List<PatientInfo> findPatientList(List<Long> idList) {
        return null;
    }

    @Override
    public boolean countRelation(Long relationId) {
        return false;
    }

    @Override
    public boolean countPatientInfo(String idCard) {
        return false;
    }

    @Override
    public long count(Long userId) {
        return 0;
    }

    @Override
    public boolean countPatientInfo(Long id) {
        return false;
    }
}
