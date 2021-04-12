package cn.edu.zucc;

import cn.edu.zucc.dto.VisitPlanAllDTO;
import cn.edu.zucc.dto.VisitPlanInfoDTO;
import cn.edu.zucc.dto.VisitPlanTimeRelDTO;
import cn.edu.zucc.dto.VisitPlanTimeRelUpdateDTO;
import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.po.VisitRelTime;

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
     * 伪删除出诊计划信息
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
     * 根据医院编号、科室编号、医生编号、出诊类型查找出诊计划列表
     *
     * @param hospitalId 医院编号
     * @param officeId   科室编号
     * @param doctorId   医生编号
     * @param visitType  出诊类型
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.po.VisitPlan>
     */
    List<VisitPlan> findVisitPlanList(Long hospitalId, Long officeId, Long doctorId, Byte visitType, Integer pageNum, Integer pageSize);

    /**
     * 判断出诊计划是否存在
     *
     * @param id 出诊编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 根据出诊编号列表获取出诊计划完整信息
     *
     * @param visitIdList 出诊编号列表
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.dto.VisitPlanAllDTO>
     */
    List<VisitPlanAllDTO> findVisitPlanListAll(List<Long> visitIdList, Integer pageNum, Integer pageSize);

    /**
     * 添加出诊计划时，添加出诊时间关系
     *
     * @param visitPlanTimeRelDTO 出诊计划时间关系参数
     * @return 是否成功
     */
    boolean insertRelation(VisitPlanTimeRelDTO visitPlanTimeRelDTO);

    /**
     * 更新出诊计划时，更新出诊计划时间关系
     *
     * @param id                        出诊时间编号
     * @param visitPlanTimeRelUpdateDTO 出诊计划时间关系更新参数
     * @return 是否成功
     */
    boolean updateRelation(Long id, VisitPlanTimeRelUpdateDTO visitPlanTimeRelUpdateDTO);

    /**
     * 伪删除出诊计划时，删除相关的出诊计划时间关系
     *
     * @param id 出诊时间编号
     * @return 是否成功
     */
    boolean deleteRealation(Long id);

    /**
     * 根据关系编号查找出诊计划时间关系
     *
     * @param id 关系编号
     * @return cn.edu.zucc.po.VisitRelTime
     */
    VisitRelTime findVisitRelTime(Long id);

    /**
     * 根据出诊编号、出诊时段、出诊日期查找出诊计划时间关系
     *
     * @param visitId     出诊编号
     * @param visitPeriod 出诊时段
     * @param visitDay    出诊日期
     * @return java.util.List<cn.edu.zucc.po.VisitRelTime>
     */
    List<VisitRelTime> findVisitRelTimeList(Long visitId, Byte visitPeriod, Date visitDay);
}
