package cn.edu.zucc;

import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.dto.VisitPatientInfoDTO;
import cn.edu.zucc.po.AppointmentInfo;

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
     * @param appointmentInfoDTO 预约信息参数
     * @return boolean
     */
    boolean insert(AppointmentInfoDTO appointmentInfoDTO);

    /**
     * 更新预约信息
     *
     * @param id          预约信息编号
     * @param visitStatus 预约信息更新参数
     * @return 是否成功
     */
    boolean update(Long id, Byte visitStatus);

    /**
     * 伪删除预约信息
     *
     * @param id 预约信息编号
     * @return 是否成功
     */
    boolean delete(Long id);

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
     * @param userId   用户编号
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.AppointmentInfo>
     */
    List<AppointmentInfo> findAppointmentListByUserId(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 判断是否已预约 若count不为0，find后判断is_delete是否为1，若为1，count=0
     *
     * @param id 预约信息编号
     * @return 是否存在
     */
    boolean count(Long id);

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
     * 获取预约患者信息列表
     *
     * @param doctorId    医生编号
     * @param visitPeriod 出诊时段
     * @param day         出诊日期
     * @param pageNum     第几页
     * @param pageSize    页大小
     * @return java.util.List<cn.edu.zucc.dto.VisitPatientInfoDTO>
     */
    List<VisitPatientInfoDTO> visitPatientInfoList(Long doctorId, Integer visitPeriod, Date day, Integer pageNum, Integer pageSize);
}
