package cn.edu.zucc.impl;

import cn.edu.zucc.DoctorService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.dto.VisitDoctorPlanDTO;
import cn.edu.zucc.dto.VisitPlanInfoDTO;
import cn.edu.zucc.mapper.VisitPlanMapper;
import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.po.VisitPlanExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class VisitPlanServiceImpl implements VisitPlanService {

    @Resource
    private VisitPlanMapper visitPlanMapper;

    @Resource
    private DoctorService doctorService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(VisitPlanInfoDTO visitPlanInfoDTO) {
        VisitPlan visitPlan = new VisitPlan();

        BeanUtils.copyProperties(visitPlanInfoDTO, visitPlan);

        visitPlan.setCreateTime(new Date());
        visitPlan.setUpdateTime(new Date());

        return visitPlanMapper.insertSelective(visitPlan) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, VisitPlanInfoDTO visitPlanInfoDTO) {
        VisitPlan visitPlan = new VisitPlan();

        BeanUtils.copyProperties(visitPlanInfoDTO, visitPlan);

        visitPlan.setId(id);
        visitPlan.setUpdateTime(new Date());

        return visitPlanMapper.updateByPrimaryKeySelective(visitPlan) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return visitPlanMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public VisitPlan findVisitPlanById(Long id) {
        return visitPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VisitPlan> findVisitPlanList(Long hospitalId, Long officeId, Long doctorId, Byte visitType, Byte visitPeriod, Date visitDay, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        VisitPlanExample example = new VisitPlanExample();
        VisitPlanExample.Criteria criteria = example.createCriteria();

        if (null != hospitalId) {
            criteria.andHospitalIdEqualTo(hospitalId);
        }
        if (null != officeId) {
            criteria.andOfficeIdEqualTo(officeId);
        }
        if (null != doctorId) {
            criteria.andDoctorIdEqualTo(doctorId);
        }
        if (null != visitType) {
            criteria.andVisitTypeEqualTo(visitType);
        }
        if (null != visitPeriod) {
            criteria.andVisitPeriodEqualTo(visitPeriod);
        }
        if (null != visitDay) {
            criteria.andVisitDayEqualTo(visitDay);
        }
        return visitPlanMapper.selectByExample(example);
    }

    @Override
    public boolean count(Long id) {
        VisitPlanExample example = new VisitPlanExample();
        example.createCriteria().andIdEqualTo(id);
        return visitPlanMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<VisitPlan> findVisitPlanList(List<Long> visitIdList) {
        VisitPlanExample example = new VisitPlanExample();
        example.createCriteria().andIdIn(visitIdList);
        return visitPlanMapper.selectByExample(example);
    }

    @Override
    public List<VisitPlan> getByTimeAndDate(Long doctorId, Byte visitPeriod, Date visitDay) {
        VisitPlanExample example = new VisitPlanExample();
        VisitPlanExample.Criteria criteria = example.createCriteria();
        if (null != doctorId) {
            criteria.andDoctorIdEqualTo(doctorId);
        }
        if (null != visitPeriod) {
            criteria.andVisitPeriodEqualTo(visitPeriod);
        }
        if (null != visitDay) {
            criteria.andVisitDayEqualTo(visitDay);
        }
        return visitPlanMapper.selectByExample(example);
    }

    @Override
    public List<Long> list(Date start, Date end) {
        VisitPlanExample example = new VisitPlanExample();
        if (null != start && null != end) {
            example.createCriteria()
                    .andVisitDayGreaterThanOrEqualTo(start)
                    .andVisitDayLessThanOrEqualTo(end);
        }
        return visitPlanMapper.selectByExample(example)
                .stream().map(VisitPlan::getId).collect(Collectors.toList());
    }

    @Override
    public VisitDoctorPlanDTO getDoctorPlan(Long doctorId, Date start, Date end) {

        VisitDoctorPlanDTO dto = new VisitDoctorPlanDTO();

        DoctorInfoDTO doctorInfoDTO = new DoctorInfoDTO();
        if (doctorService.count(doctorId)) {
            BeanUtils.copyProperties(doctorService.findDoctorById(doctorId), doctorInfoDTO);
        }
        //添加医生信息
        dto.setDoctorInfoDTO(doctorInfoDTO);

        dto.setVisitPlanInfoDTOList(getVisitPlanInfoDTO(doctorId, start, end));

        return dto;
    }

    private List<VisitPlanInfoDTO> getVisitPlanInfoDTO(Long doctorId, Date start, Date end) {

        List<VisitPlanInfoDTO> list = new ArrayList<>();

        VisitPlanExample example = new VisitPlanExample();
        VisitPlanExample.Criteria criteria = example.createCriteria();

        if (null != doctorId) {
            criteria.andDoctorIdEqualTo(doctorId);
        }
        if (null != start && null != end) {
            criteria.andVisitDayGreaterThanOrEqualTo(start);
            criteria.andVisitDayLessThanOrEqualTo(end);
        }
        //对VisitPlan列表进行遍历，将每个VisitPlan对象转换为VisitPlanInfoDTO对象
        visitPlanMapper.selectByExample(example).forEach((VisitPlan item) -> {
            VisitPlanInfoDTO visitPlanInfoDTO = new VisitPlanInfoDTO();

            BeanUtils.copyProperties(item, visitPlanInfoDTO);

            list.add(visitPlanInfoDTO);
        });

        return list;
    }
}
