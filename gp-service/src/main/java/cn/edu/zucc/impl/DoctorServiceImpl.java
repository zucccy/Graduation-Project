package cn.edu.zucc.impl;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.DoctorService;
import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.DoctorInfoMapper;
import cn.edu.zucc.po.DoctorInfo;
import cn.edu.zucc.po.DoctorInfoExample;
import cn.edu.zucc.vo.DoctorVO;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    DoctorInfoMapper doctorInfoMapper;

    @Resource
    OfficeService officeService;

    @Resource
    HospitalService hospitalService;

    @Resource
    AppointmentInfoService appointmentInfoService;

    @Resource
    VisitPlanService visitPlanService;

    @Override
    public DoctorInfo findDoctorById(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("医生不存在");
        }

        return doctorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public DoctorVO findDoctorVOById(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("医生不存在");
        }

        DoctorVO doctorVO = new DoctorVO();
        DoctorInfo doctorInfo = findDoctorById(id);
        BeanUtils.copyProperties(doctorInfo, doctorVO);
        doctorVO.setOfficeName(officeService.findOfficeById(doctorInfo.getOfficeId()).getOfficeName());
        doctorVO.setHospitalName(hospitalService.findHospitalById(doctorInfo.getHospitalId()).getHospitalName());
        return doctorVO;
    }

    @Override
    public List<DoctorVO> findDoctorList(String doctorName, Integer pageNum, Integer pageSize) {

        if (null != pageNum && null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<DoctorVO> doctorVOList = doctorInfoMapper.findDoctorList(null, null, doctorName);
        return doctorVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = new DoctorInfo();

        BeanUtils.copyProperties(doctorInfoDTO, doctorInfo);

        doctorInfo.setCreateTime(new Date());
        doctorInfo.setUpdateTime(new Date());

        return doctorInfoMapper.insertSelective(doctorInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = new DoctorInfo();

        BeanUtils.copyProperties(doctorInfoDTO, doctorInfo);

        doctorInfo.setId(id);
        doctorInfo.setUpdateTime(new Date());

        return doctorInfoMapper.updateByPrimaryKeySelective(doctorInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("医生不存在");
        }
        if (appointmentInfoService.countByDoctorId(id) || visitPlanService.countByDoctorId(id)) {
            throw new FormException("该医生有预约信息或出诊计划，不能删除");
        }
        return doctorInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean count(Long id) {
        DoctorInfoExample example = new DoctorInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return doctorInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<DoctorVO> findDoctorList(Long officeId, Integer pageNum, Integer pageSize) {
        List<DoctorVO> doctorVOList;
        if (null == officeId) {
            throw new FormException();
        }
        if (!officeService.count(officeId)) {
            throw new SourceNotFoundException("科室不存在");
        }
        if (null != pageNum && null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        }
        doctorVOList = doctorInfoMapper.findDoctorList(officeId, null, null);
        return doctorVOList;
    }

    @Override
    public List<DoctorVO> findDoctorListByHosId(Long hospitalId, Integer pageNum, Integer pageSize) {

        List<DoctorVO> doctorVOList;
        if (null == hospitalId) {
            throw new FormException();
        }
        if (!hospitalService.count(hospitalId)) {
            throw new SourceNotFoundException("医院不存在");
        }
        if (null != pageNum && null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        }
        doctorVOList = doctorInfoMapper.findDoctorList(null, hospitalId, null);
        return doctorVOList;
    }

    @Override
    public List<DoctorInfo> findDoctorInfoList(List<Long> doctorIdList) {
        DoctorInfoExample example = new DoctorInfoExample();
        example.createCriteria().andIdIn(doctorIdList);
        List<DoctorInfo> doctorInfoList = doctorInfoMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(doctorInfoList)) {
            return new ArrayList<>();
        }
        return doctorInfoList;
    }
}
