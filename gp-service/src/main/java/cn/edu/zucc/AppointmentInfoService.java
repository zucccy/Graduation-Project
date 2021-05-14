package cn.edu.zucc;

import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.dto.VisitPatientInfoDTO;
import cn.edu.zucc.po.AppointmentInfo;
import cn.edu.zucc.vo.MyAppointmentListVO;
import cn.edu.zucc.vo.MyAppointmentVO;

import java.util.Date;
import java.util.List;

/**
 * Description: 预约相关接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface AppointmentInfoService {

    /**
     * 创建预约信息
     *
     * @param userId             用户编号
     * @param appointmentInfoDTO 预约信息参数
     * @return boolean
     */
    boolean insert(Long userId, AppointmentInfoDTO appointmentInfoDTO);

    /**
     * 更新预约信息
     *
     * @param userId      用户编号
     * @param id          预约信息编号
     * @param visitStatus 预约信息更新参数
     * @return 是否成功
     */
    boolean update(Long userId, Long id, Byte visitStatus);

    /**
     * 删除预约信息
     *
     * @param id 预约信息编号
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 判断预约信息是否存在
     *
     * @param id 预约编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 判断预约信息是否存在
     *
     * @param doctorId 医生编号
     * @return 是否存在
     */
    boolean countByDoctorId(Long doctorId);

    /**
     * 判断预约信息是否存在
     *
     * @param userId 用户编号
     * @return 是否存在
     */
    boolean countByUserId(Long userId);

    /**
     * 根据预约编号查找预约信息
     *
     * @param id 预约信息编号
     * @return cn.edu.zucc.po.AppointmentInfo
     */
    AppointmentInfo findAppointmentById(Long id);

    /**
     * 根据用户编号查找预约信息列表
     *
     * @param userId      用户编号
     * @param visitStatus 预约状态
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.po.AppointmentInfo>
     */
    List<AppointmentInfo> findAppointmentListByUserId(Long userId, Byte visitStatus, Integer pageNum, Integer pageSize);

    /**
     * 判断该患者是否已预约
     *
     * @param patientId 患者编号
     * @param visitId   出诊编号
     * @return 是否存在
     */
    boolean count(Long patientId, Long visitId);

    /**
     * 根据医生编号查找预约信息列表
     *
     * @param doctorId    医生编号
     * @param visitStatus 预约状态
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.po.AppointmentInfo>
     */
    List<AppointmentInfo> findAppointmentListByDoctorId(Long doctorId, Byte visitStatus, Integer pageNum, Integer pageSize);

    /**
     * 根据患者编号、预约状态获取预约信息列表
     *
     * @param patientId   患者编号
     * @param visitStatus 预约状态
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.po.AppointmentInfo>
     */
    List<AppointmentInfo> findAppointmentList(Long patientId, Byte visitStatus, Integer pageNum, Integer pageSize);

    /**
     * 获取（某天）医生（某时段）预约患者信息列表
     *
     * @param doctorId    医生编号
     * @param visitPeriod 出诊时段
     * @param visitDay    出诊日期
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.dto.VisitPatientInfoDTO>
     */
    List<VisitPatientInfoDTO> visitPatientInfoList(Long doctorId, Byte visitPeriod, Date visitDay, Integer pageNum, Integer pageSize);

    /**
     * 根据出诊编号列表查找预约信息列表
     *
     * @param visitIdList 出诊编号列表
     * @return java.util.List<cn.edu.zucc.po.AppointmentInfo>
     */
    List<AppointmentInfo> findAppointmentList(List<Long> visitIdList);

    /**
     * 转换获取我的预约对象
     *
     * @param appointmentInfo 预约信息
     * @return cn.edu.zucc.vo.MyAppointmentVO
     */
    MyAppointmentListVO convertMyAppointmentListVO(AppointmentInfo appointmentInfo);

    /**
     * 获取用户此次预约的具体信息
     *
     * @param id 预约编号
     * @return cn.edu.zucc.vo.MyAppointmentVO
     */
    MyAppointmentVO convertMyAppointmentVO(Long id);
}
