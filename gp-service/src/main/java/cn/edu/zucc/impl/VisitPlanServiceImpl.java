package cn.edu.zucc.impl;

import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.VisitPlanAllDTO;
import cn.edu.zucc.dto.VisitPlanInfoDTO;
import cn.edu.zucc.dto.VisitPlanTimeRelDTO;
import cn.edu.zucc.dto.VisitPlanTimeRelUpdateDTO;
import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.po.VisitRelTime;
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
public class VisitPlanServiceImpl implements VisitPlanService {
    @Override
    public boolean insert(VisitPlanInfoDTO visitPlanInfoDTO) {
        return false;
    }

    @Override
    public boolean update(Long id, VisitPlanInfoDTO visitPlanInfoDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public VisitPlan findVisitPlanById(Long id) {
        return null;
    }

    @Override
    public List<VisitPlan> findVisitPlanList(Long hospitalId, Long officeId, Long doctorId, Byte visitType, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public boolean count(Long id) {
        return false;
    }

    @Override
    public List<VisitPlanAllDTO> findVisitPlanListAll(List<Long> visitIdList) {
        return null;
    }

    @Override
    public boolean insertRelation(VisitPlanTimeRelDTO visitPlanTimeRelDTO) {
        return false;
    }

    @Override
    public boolean updateRelation(Long id, VisitPlanTimeRelUpdateDTO visitPlanTimeRelUpdateDTO) {
        return false;
    }

    @Override
    public boolean deleteRealation(Long id) {
        return false;
    }

    @Override
    public VisitRelTime findVisitRelTime(Long id) {
        return null;
    }

    @Override
    public List<VisitRelTime> findVisitRelTimeList(Long visitId, Byte visitPeriod, Date visitDay) {
        return null;
    }

    @Override
    public List<Long> list(Date start, Date end) {
        return null;
    }
}
