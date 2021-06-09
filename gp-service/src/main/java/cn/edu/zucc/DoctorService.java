package cn.edu.zucc;

import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.po.DoctorInfo;
import cn.edu.zucc.vo.DoctorVO;
import cn.edu.zucc.vo.MyAppointmentListVO;

import java.util.List;

/**
 * Description: 医生相关接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface DoctorService {

    /**
     * 查找所有医生列表
     *
     * @param doctorName
     * @param pageNum
     * @param pageSize
     * @return java.util.List<cn.edu.zucc.vo.DoctorVO>
     */
    List<DoctorVO> getAllDoctors(String doctorName, Integer pageNum, Integer pageSize);

    /**
     * 根据医生编号查找医生信息
     *
     * @param id 医生编号
     * @return cn.edu.zucc.po.DoctorInfo
     */
    DoctorInfo findDoctorById(Long id);

    /**
     * 根据医生编号查找医生综合信息
     *
     * @param id 医生编号
     * @return cn.edu.zucc.vo.DoctorVO
     */
    DoctorVO findDoctorVOById(Long id);

    /**
     * 分页搜索医生信息 按医生名字/所属医院名字/科室名模糊查询
     *
     * @param doctorName 医生名称
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.po.DoctorInfo>
     */
    List<DoctorVO> findDoctorList(String doctorName, Integer pageNum, Integer pageSize);

    /**
     * 添加医生信息
     *
     * @param doctorInfoDTO 医生信息参数
     * @return 是否成功
     */
    boolean insert(DoctorInfoDTO doctorInfoDTO);

    /**
     * 更新医生信息
     *
     * @param id            医生编号
     * @param doctorInfoDTO 医生信息参数
     * @return 是否成功
     */
    boolean update(Long id, DoctorInfoDTO doctorInfoDTO);

    /**
     * 删除医生信息
     *
     * @param id 医生编号
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 判断医生是否存在
     *
     * @param id 医生编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 查找同医院同科室医生列表(除了该医生以外)
     *
     * @param officeId   科室编号
     * @param id         医生编号
     * @param hospitalId 医院编号
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.vo.DoctorVO>
     */
    List<DoctorVO> findDoctorList(Long officeId, Long id, Long hospitalId, Integer pageNum, Integer pageSize);

    /**
     * 根据科室编号查找医生信息
     *
     * @param officeId 科室编号
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.vo.DoctorVO>
     */
    List<DoctorVO> findDoctorList(Long officeId, Integer pageNum, Integer pageSize);

    /**
     * 根据医院编号查找医生综合信息
     *
     * @param hospitalId 医院编号
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.vo.DoctorVO>
     */
    List<DoctorVO> findDoctorListByHosId(Long hospitalId, Integer pageNum, Integer pageSize);

    /**
     * 根据医生编号列表查找医生信息列表
     *
     * @param doctorIdList 医生编号列表
     * @return java.util.List<cn.edu.zucc.po.DoctorInfo>
     */
    List<DoctorInfo> findDoctorInfoList(List<Long> doctorIdList);

    /**
     * 根据医生编号获取预约集合
     *
     * @param phone 医生手机号
     * @return java.util.List<cn.edu.zucc.vo.MyAppointmentListVO>
     */
    List<MyAppointmentListVO> getAppointmentListByDoctorId(String phone);
}
