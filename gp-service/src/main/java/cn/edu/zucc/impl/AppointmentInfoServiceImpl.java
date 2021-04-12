package cn.edu.zucc.impl;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.dto.VisitPatientInfoDTO;
import cn.edu.zucc.po.AppointmentInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class AppointmentInfoServiceImpl implements AppointmentInfoService {
    @Override
    public boolean insert(AppointmentInfoDTO appointmentInfoDTO) {
        return false;
    }

    @Override
    public boolean update(Long id, Byte visitStatus) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public AppointmentInfo findAppointmentById(Long id) {
        return null;
    }

    @Override
    public List<AppointmentInfo> findAppointmentListByUserId(Long userId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public boolean count(Long id) {
        return false;
    }

    @Override
    public List<AppointmentInfo> findAppointmentListByDoctorId(Long doctorId, Byte visitStatus, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<AppointmentInfo> findAppointmentList(Long patientId, Byte visitStatus, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<VisitPatientInfoDTO> visitPatientInfoList(Long doctorId, Integer visitPeriod, Date day, Integer pageNum, Integer pageSize) {
        return null;
    }
}
