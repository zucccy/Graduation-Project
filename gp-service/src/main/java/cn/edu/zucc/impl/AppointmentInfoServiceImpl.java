package cn.edu.zucc.impl;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.DoctorService;
import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.dto.VisitPatientInfoDTO;
import cn.edu.zucc.enums.AppointmentEnum;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.AppointmentInfoMapper;
import cn.edu.zucc.mapper.PatientInfoMapper;
import cn.edu.zucc.mapper.VisitPlanMapper;
import cn.edu.zucc.po.AppointmentInfo;
import cn.edu.zucc.po.AppointmentInfoExample;
import cn.edu.zucc.po.DoctorInfo;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.Office;
import cn.edu.zucc.po.PatientInfo;
import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.vo.MyAppointmentListVO;
import cn.edu.zucc.vo.MyAppointmentVO;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private HospitalService hospitalService;

    @Resource
    private OfficeService officeService;

    @Resource
    private PatientInfoService patientInfoService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Long userId, AppointmentInfoDTO appointmentInfoDTO) {
        AppointmentInfo appointmentInfo = new AppointmentInfo();

        BeanUtils.copyProperties(appointmentInfoDTO, appointmentInfo);

        appointmentInfo.setUserId(userId);
        appointmentInfo.setCreateTime(new Date());
        appointmentInfo.setUpdateTime(new Date());

        appointmentInfoMapper.insertSelective(appointmentInfo);
        //获取我的预约对象
        MyAppointmentListVO myAppointmentListVO = convertMyAppointmentListVO(appointmentInfo);

        //将对象转换为Json字符串
        String myAppointmentVOListJson = JSONUtil.toJsonStr(myAppointmentListVO);
        //key = 用户编号:预约编号
        String key = appointmentInfo.getUserId() + ":" + appointmentInfo.getId();
        redisTemplate.opsForValue().set(key, myAppointmentVOListJson);
//        redisTemplate.opsForZSet().range("hospitalInfo", 0, 8);

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long userId, Long id, Byte visitStatus) {
        AppointmentInfo appointmentInfo = new AppointmentInfo();

        appointmentInfo.setId(id);
        appointmentInfo.setVisitStatus(visitStatus);
        appointmentInfo.setUpdateTime(new Date());
        appointmentInfoMapper.updateByPrimaryKeySelective(appointmentInfo);

        //修改redis
        String key = userId + ":" + id;
        String cacheValue = (String) redisTemplate.opsForValue().get(key);
        MyAppointmentListVO myAppointmentListVO = JSON.parseObject(cacheValue, MyAppointmentListVO.class);
        myAppointmentListVO.setVisitStatus(appointmentInfo.getVisitStatus());

        //将对象转换为Json字符串
        String myAppointmentVOListJson = JSONUtil.toJsonStr(myAppointmentListVO);
        //key = 用户编号:预约编号
        redisTemplate.opsForValue().set(key, myAppointmentVOListJson);
        return true;
    }

    @Override
    public boolean updateByDoctorId(Long id, Byte visitStatus) {

        AppointmentInfo appointmentInfo = findAppointmentById(id);
        appointmentInfo.setVisitStatus(visitStatus);
        appointmentInfo.setUpdateTime(new Date());
        appointmentInfoMapper.updateByPrimaryKeySelective(appointmentInfo);


        //修改redis
        String key = appointmentInfo.getUserId() + ":" + id;
        String cacheValue = (String) redisTemplate.opsForValue().get(key);
        MyAppointmentListVO myAppointmentListVO = JSON.parseObject(cacheValue, MyAppointmentListVO.class);
        myAppointmentListVO.setVisitStatus(appointmentInfo.getVisitStatus());

        //将对象转换为Json字符串
        String myAppointmentVOListJson = JSONUtil.toJsonStr(myAppointmentListVO);
        //key = 用户编号:预约编号
        redisTemplate.opsForValue().set(key, myAppointmentVOListJson);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    public boolean countByDoctorId(Long doctorId) {
        AppointmentInfoExample example = new AppointmentInfoExample();
        example.createCriteria().andDoctorIdEqualTo(doctorId);
        return appointmentInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public boolean countByUserId(Long userId) {
        AppointmentInfoExample example = new AppointmentInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return appointmentInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public AppointmentInfo findAppointmentById(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("预约信息不存在");
        }
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

        AppointmentInfoExample example = new AppointmentInfoExample();
        AppointmentInfoExample.Criteria criteria = example.createCriteria();

        //得到符合要求的出诊编号列表
        List<Long> visitIdList = visitPlanService.getByTimeAndDate(doctorId, visitPeriod, visitDay)
                .stream().map(VisitPlan::getId).collect(Collectors.toList());
        //得到预约信息列表
        PageHelper.startPage(pageNum, pageSize);
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

    @Override
    public MyAppointmentListVO convertMyAppointmentListVO(AppointmentInfo appointmentInfo) {
        MyAppointmentListVO myAppointmentListVO = new MyAppointmentListVO();

        //预约编号、预约状态
        myAppointmentListVO.setId(appointmentInfo.getId());
        myAppointmentListVO.setVisitStatus(appointmentInfo.getVisitStatus());

        //查找医生名称、医院id、科室id
        Long doctorId = appointmentInfo.getDoctorId();
        DoctorInfo doctorInfo = doctorService.findDoctorById(doctorId);
        myAppointmentListVO.setDoctorName(doctorInfo.getDoctorName());

        //查找医院名
        Long hospitalId = doctorInfo.getHospitalId();
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        myAppointmentListVO.setHospitalName(hospital.getHospitalName());
        myAppointmentListVO.setHospitalId(hospitalId);

        //查找科室名
        Long officeId = doctorInfo.getOfficeId();
        Office office = officeService.findOfficeById(officeId);
        myAppointmentListVO.setOfficeName(office.getOfficeName());
        myAppointmentListVO.setOfficeId(officeId);

        //查找患者名称、患者手机号
        Long patientId = appointmentInfo.getPatientId();
        PatientInfo patientInfo = patientInfoService.findPatientInfoById(patientId);
        myAppointmentListVO.setPatientName(patientInfo.getPatientName());
        myAppointmentListVO.setPhone(patientInfo.getPhone());

        //查找具体就诊时间和出诊日期
        Long visitId = appointmentInfo.getVisitId();
        VisitPlan visitPlan = visitPlanService.findVisitPlanById(visitId);
        myAppointmentListVO.setTimePeriod(visitPlan.getTimePeriod());
        //flag为0说明可以进行修改状态完成操作
        if (new Date().before(visitPlan.getVisitDay())) {
            myAppointmentListVO.setFlag(1);
        } else {
            myAppointmentListVO.setFlag(0);
        }

        return myAppointmentListVO;
    }

    @Override
    public MyAppointmentVO convertMyAppointmentVO(Long id) {
        AppointmentInfo appointmentInfo = findAppointmentById(id);
        MyAppointmentVO myAppointmentVO = new MyAppointmentVO();

        //查找预约状态、预约时间
        myAppointmentVO.setVisitStatus(appointmentInfo.getVisitStatus());
        myAppointmentVO.setCreateTime(appointmentInfo.getCreateTime());

        //查找医生名称、医院id、科室id
        Long doctorId = appointmentInfo.getDoctorId();
        DoctorInfo doctorInfo = doctorService.findDoctorById(doctorId);
        myAppointmentVO.setDoctorName(doctorInfo.getDoctorName());

        //查找医院名、医院地址
        Long hospitalId = doctorInfo.getHospitalId();
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        myAppointmentVO.setHospitalName(hospital.getHospitalName());
        myAppointmentVO.setHospitalId(hospitalId);
        myAppointmentVO.setAddress(hospital.getAddress());

        //查找科室名
        Long officeId = doctorInfo.getOfficeId();
        Office office = officeService.findOfficeById(officeId);
        myAppointmentVO.setOfficeName(office.getOfficeName());
        myAppointmentVO.setOfficeId(officeId);

        //查找患者名称、患者手机号、身份证号、性别
        Long patientId = appointmentInfo.getPatientId();
        PatientInfo patientInfo = patientInfoService.findPatientInfoById(patientId);
        myAppointmentVO.setPatientName(patientInfo.getPatientName());
        myAppointmentVO.setPhone(patientInfo.getPhone());
        myAppointmentVO.setIdCard(patientInfo.getIdCard());
        myAppointmentVO.setSex(patientInfo.getSex());

        //查找具体就诊时间
        Long visitId = appointmentInfo.getVisitId();
        VisitPlan visitPlan = visitPlanService.findVisitPlanById(visitId);
        myAppointmentVO.setTimePeriod(visitPlan.getTimePeriod());

        return myAppointmentVO;
    }
}
