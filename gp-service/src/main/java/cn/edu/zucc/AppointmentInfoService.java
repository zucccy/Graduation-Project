package cn.edu.zucc;

import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.dto.AppointmentInfoUpdateDTO;
import cn.edu.zucc.po.AppointmentInfo;

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
     * @param id                       预约信息编号
     * @param appointmentInfoUpdateDTO 预约信息更新参数
     * @return 是否成功
     */
    boolean update(Long id, AppointmentInfoUpdateDTO appointmentInfoUpdateDTO);

    /**
     * 伪删除预约信息
     *
     * @param id
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 根据预约编号查找预约信息
     *
     * @param id 预约编号
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
     * 判断预约信息是否存在 若count不为0，find后判断is_delete是否为1，若为1，count=0
     *
     * @param id 预约信息编号
     * @return 是否存在
     */
    boolean count(Long id);
}
