package cn.edu.zucc.impl;

import cn.edu.zucc.DoctorService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.VisitPlanInfoDTO;
import cn.edu.zucc.enums.VisitPeriodEnum;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.VisitPlanMapper;
import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.po.VisitPlanExample;
import cn.edu.zucc.vo.DoctorVO;
import cn.edu.zucc.vo.VisitDoctorPlanVO;
import cn.edu.zucc.vo.VisitPlanVO;
import cn.edu.zucc.vo.VisitPlanVOList;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
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
        if (!count(id)) {
            throw new SourceNotFoundException("出诊计划不存在");
        }
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
    public boolean countByDoctorId(Long doctorId) {
        VisitPlanExample example = new VisitPlanExample();
        example.createCriteria().andDoctorIdEqualTo(doctorId);
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
    public VisitDoctorPlanVO getDoctorPlan(Long doctorId) {

        VisitDoctorPlanVO vo = new VisitDoctorPlanVO();
        if (!doctorService.count(doctorId)) {
            throw new SourceNotFoundException("医生不存在");
        }
        DoctorVO doctorVO = doctorService.findDoctorVOById(doctorId);

        //添加医生信息
        vo.setDoctorVO(doctorVO);

        vo.setVisitPlanVOList(getVisitPlanVOList(doctorId));

        return vo;
    }

    private VisitPlanVOList getVisitPlanVOList(Long doctorId) {

        VisitPlanVOList visitPlanVOList = new VisitPlanVOList();
        List<VisitPlanVO> visitPlanVOAmList = new ArrayList<>(7);
        List<VisitPlanVO> visitPlanVOPmList = new ArrayList<>(7);
        List<VisitPlanVO> visitPlanVOEveList = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            Date myDate = Date.from(LocalDate.now().plusDays(i).atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
            VisitPlanVO visitPlanAmVO = new VisitPlanVO();
            VisitPlanVO visitPlanPmVO = new VisitPlanVO();
            VisitPlanVO visitPlanEveVO = new VisitPlanVO();
            visitPlanAmVO.setVisitPeriod(Byte.parseByte("1"));
            visitPlanPmVO.setVisitPeriod(Byte.parseByte("2"));
            visitPlanEveVO.setVisitPeriod(Byte.parseByte("3"));
            visitPlanAmVO.setVisitDay(myDate);
            visitPlanPmVO.setVisitDay(myDate);
            visitPlanEveVO.setVisitDay(myDate);
            visitPlanVOAmList.add(visitPlanAmVO);
            visitPlanVOPmList.add(visitPlanPmVO);
            visitPlanVOEveList.add(visitPlanEveVO);
        }
        VisitPlanExample example = new VisitPlanExample();
        VisitPlanExample.Criteria criteria = example.createCriteria();

        if (null != doctorId) {
            criteria.andDoctorIdEqualTo(doctorId);
        } else {
            throw new FormException();
        }
        criteria.andVisitDayGreaterThanOrEqualTo(Date.from(LocalDate.now().atStartOfDay(ZoneOffset.ofHours(8)).toInstant()));
        criteria.andVisitDayLessThanOrEqualTo(Date.from(LocalDate.now().plus(1, ChronoUnit.WEEKS).atStartOfDay(ZoneOffset.ofHours(8)).toInstant()));
        List<VisitPlan> visitPlans = visitPlanMapper.selectByExample(example);
        //对VisitPlan列表进行遍历，将每个VisitPlan对象转换为VisitPlanVO对象
        visitPlans.forEach((VisitPlan item) -> {
            VisitPlanVO visitPlanVO = new VisitPlanVO();
            BeanUtils.copyProperties(item, visitPlanVO);
            //若为早上，则覆盖早上集合元素
            if (visitPlanVO.getVisitPeriod() == Byte.parseByte(String.valueOf(VisitPeriodEnum.MORNING.getTypes()))) {
                visitPlanVOAmList.forEach(index -> {
                    if (index.getVisitDay().equals(visitPlanVO.getVisitDay())) {
                        Collections.replaceAll(visitPlanVOAmList, index, visitPlanVO);
                    }
                });
            }
            //若为下午，则覆盖下午集合元素
            else if (visitPlanVO.getVisitPeriod() == Byte.parseByte(String.valueOf(VisitPeriodEnum.AFTERNOON.getTypes()))) {
                visitPlanVOPmList.forEach(index -> {
                    if (index.getVisitDay().equals(visitPlanVO.getVisitDay())) {
                        Collections.replaceAll(visitPlanVOPmList, index, visitPlanVO);
                    }
                });
            }
            //若为晚上，则覆盖晚上集合元素
            else if (visitPlanVO.getVisitPeriod() == Byte.parseByte(String.valueOf(VisitPeriodEnum.NIGHT.getTypes()))) {
                visitPlanVOEveList.forEach(index -> {
                    if (index.getVisitDay().equals(visitPlanVO.getVisitDay())) {
                        Collections.replaceAll(visitPlanVOEveList, index, visitPlanVO);
                    }
                });
            }
        });
        visitPlanVOList.setVisitPlanVOAmList(visitPlanVOAmList);
        visitPlanVOList.setVisitPlanVOPmList(visitPlanVOPmList);
        visitPlanVOList.setVisitPlanVOEveList(visitPlanVOEveList);
        return visitPlanVOList;
    }

}
