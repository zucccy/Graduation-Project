package cn.edu.zucc;

import cn.edu.zucc.dto.VisitDoctorPlanDTO;
import cn.edu.zucc.dto.VisitPlanInfoDTO;
import cn.edu.zucc.po.VisitPlan;

import java.util.Date;
import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
public interface VisitPlanService {

    /**
     * 添加出诊计划信息
     *
     * @param visitPlanInfoDTO 出诊计划信息参数
     * @return 是否成功
     */
    boolean insert(VisitPlanInfoDTO visitPlanInfoDTO);

    /**
     * 更新出诊计划信息
     *
     * @param id               出诊编号
     * @param visitPlanInfoDTO 出诊计划信息参数
     * @return 是否成功
     */
    boolean update(Long id, VisitPlanInfoDTO visitPlanInfoDTO);

    /**
     * 删除出诊计划信息
     *
     * @param id 出诊编号
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 根据出诊编号查找出诊信息
     *
     * @param id 出诊编号
     * @return cn.edu.zucc.po.VisitPlan
     */
    VisitPlan findVisitPlanById(Long id);

    /**
     * 查找出诊计划列表
     *
     * @param hospitalId  医院编号
     * @param officeId    科室编号
     * @param doctorId    医生编号
     * @param visitType   出诊计划类型
     * @param visitPeriod 出诊时段
     * @param visitDay    出诊日期
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.po.VisitPlan>
     */
    List<VisitPlan> findVisitPlanList(Long hospitalId, Long officeId, Long doctorId, Byte visitType, Byte visitPeriod, Date visitDay, Integer pageNum, Integer pageSize);

    /**
     * 判断出诊计划是否存在
     *
     * @param id 出诊编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 根据出诊编号列表获取出诊计划信息列表
     *
     * @param visitIdList 出诊编号列表
     * @return java.util.List<cn.edu.zucc.po.VisitPlan>
     */
    List<VisitPlan> findVisitPlanList(List<Long> visitIdList);

    /**
     * 获取医生某天出诊计划信息
     *
     * @param doctorId    医生编号
     * @param visitPeriod 出诊时段
     * @param visitDay    出诊日期
     * @return java.util.List<cn.edu.zucc.po.VisitPlan>
     */
    List<VisitPlan> getByTimeAndDate(Long doctorId, Byte visitPeriod, Date visitDay);

    /**
     * 获取某段时间出诊计划的编号列表
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 出诊计划编号
     */
    List<Long> list(Date start, Date end);

    /**
     * 获取一段时间内医生出诊计划
     *
     * @param doctorId 医生编号
     * @param start    开始日期
     * @param end      结束日期
     * @return VisitDoctorPlanDTO
     */
    VisitDoctorPlanDTO getDoctorPlan(Long doctorId, Date start, Date end);

}
