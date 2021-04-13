package cn.edu.zucc.impl;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.dto.VisitPatientInfoDTO;
import cn.edu.zucc.enums.AppointmentEnum;
import cn.edu.zucc.mapper.AppointmentInfoMapper;
import cn.edu.zucc.mapper.PatientInfoMapper;
import cn.edu.zucc.mapper.VisitPlanMapper;
import cn.edu.zucc.po.AppointmentInfo;
import cn.edu.zucc.po.AppointmentInfoExample;
import cn.edu.zucc.po.PatientInfo;
import cn.edu.zucc.po.VisitPlan;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class AppointmentInfoServiceImpl implements AppointmentInfoService {

    @Resource
    private AppointmentInfoMapper appointmentInfoMapper;

    @Resource
    private PatientInfoMapper patientInfoMapper;

    @Resource
    private VisitPlanMapper visitPlanMapper;

    @Resource
    private VisitPlanService visitPlanService;

    @Override
    public boolean insert(AppointmentInfoDTO appointmentInfoDTO) {
        AppointmentInfo appointmentInfo = new AppointmentInfo();

        BeanUtils.copyProperties(appointmentInfoDTO, appointmentInfo);

        appointmentInfo.setCreateTime(new Date());
        appointmentInfo.setUpdateTime(new Date());

        return appointmentInfoMapper.insertSelective(appointmentInfo) > 0;
    }

    @Override
    public boolean update(Long id, Byte visitStatus) {
        AppointmentInfo appointmentInfo = new AppointmentInfo();

        appointmentInfo.setId(id);
        appointmentInfo.setVisitStatus(visitStatus);
        appointmentInfo.setUpdateTime(new Date());

        return appointmentInfoMapper.updateByPrimaryKeySelective(appointmentInfo) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return appointmentInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean count(Long id) {
        AppointmentInfoExample example = new AppointmentInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return appointmentInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public AppointmentInfo findAppointmentById(Long id) {
        return appointmentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AppointmentInfo> findAppointmentListByUserId(Long userId, Byte visitStatus, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AppointmentInfoExample example = new AppointmentInfoExample();

        //倒序由近到远
        example.setOrderByClause("create_time desc");
        AppointmentInfoExample.Criteria criteria = example.createCriteria();

        if (null != userId) {
            criteria.andUserIdEqualTo(userId);
        }
        if (null != visitStatus) {
            criteria.andVisitStatusEqualTo(visitStatus);
        }
        return appointmentInfoMapper.selectByExample(example);
    }

    @Override
    public boolean count(Long patientId, Long visitId) {
        AppointmentInfoExample example = new AppointmentInfoExample();
        example.createCriteria()
                .andPatientIdEqualTo(patientId)
                .andVisitIdEqualTo(visitId)
                // 已预约
                .andVisitStatusEqualTo(AppointmentEnum.WAITING.getStatus().byteValue());
        return appointmentInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<AppointmentInfo> findAppointmentListByDoctorId(Long doctorId, Byte visitStatus, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AppointmentInfoExample example = new AppointmentInfoExample();

        //倒序由近到远
        example.setOrderByClause("create_time desc");

        AppointmentInfoExample.Criteria criteria = example.createCriteria();

        if (null != doctorId) {
            criteria.andDoctorIdEqualTo(doctorId);
        }
        if (null != visitStatus) {
            criteria.andVisitStatusEqualTo(visitStatus);
        }

        return appointmentInfoMapper.selectByExample(example);
    }

    @Override
    public List<AppointmentInfo> findAppointmentList(Long patientId, Byte visitStatus, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AppointmentInfoExample example = new AppointmentInfoExample();

        //倒序由近到远
        example.setOrderByClause("create_time desc");

        AppointmentInfoExample.Criteria criteria = example.createCriteria();

        if (null != patientId) {
            criteria.andPatientIdEqualTo(patientId);
        }
        if (null != visitStatus) {
            criteria.andVisitStatusEqualTo(visitStatus);
        }

        return appointmentInfoMapper.selectByExample(example);
    }

    @Override
    public List<VisitPatientInfoDTO> visitPatientInfoList(Long doctorId, Byte visitPeriod, Date visitDay, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AppointmentInfoExample example = new AppointmentInfoExample();
        AppointmentInfoExample.Criteria criteria = example.createCriteria();

        //得到符合要求的出诊编号列表
        List<Long> visitIdList = visitPlanService.getByTimeAndDate(doctorId, visitPeriod, visitDay)
                .stream().map(VisitPlan::getId).collect(Collectors.toList());
        //得到预约信息列表
        List<AppointmentInfo> list = findAppointmentList(visitIdList);

        return list.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentInfo> findAppointmentList(List<Long> visitIdList) {
        AppointmentInfoExample example = new AppointmentInfoExample();
        example.createCriteria().andVisitIdIn(visitIdList);
        return appointmentInfoMapper.selectByExample(example);
    }

    private VisitPatientInfoDTO convert(AppointmentInfo appointmentInfo) {
        VisitPatientInfoDTO dto = new VisitPatientInfoDTO();

        dto.setAppointmentId(appointmentInfo.getId());
        dto.setVisitStatus(appointmentInfo.getVisitStatus());

        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(appointmentInfo.getPatientId());
        if (null != patientInfo) {
            BeanUtils.copyProperties(patientInfo, dto);
        }

        VisitPlan visitPlan = visitPlanMapper.selectByPrimaryKey(appointmentInfo.getVisitId());
        if (null != visitPlan) {
            BeanUtils.copyProperties(visitPlan, dto);
        }

        return dto;
    }
}
