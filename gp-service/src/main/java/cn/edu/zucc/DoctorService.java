package cn.edu.zucc;

import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.po.DoctorInfo;

import java.util.List;

/**
 * Description: 医生相关接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface DoctorService {

    /**
     * 根据医生编号查找医生信息
     *
     * @param id 医生编号
     * @return cn.edu.zucc.po.DoctorInfo
     */
    DoctorInfo findDoctorById(Long id);

    /**
     * 根据医生名称查找医生列表
     *
     * @param doctorName 医生名称
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.po.DoctorInfo>
     */
    List<DoctorInfo> findDoctorList(String doctorName, Integer pageNum, Integer pageSize);

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
     * 根据科室编号查找医生信息
     *
     * @param officeId 科室编号
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.DoctorInfo>
     */
    List<DoctorInfo> findDoctorList(Long officeId, Integer pageNum, Integer pageSize);

    /**
     * 根据医院编号查找医生信息
     *
     * @param hospitalId 医院编号
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.po.DoctorInfo>
     */
    List<DoctorInfo> findDoctorListByHosId(Long hospitalId, Integer pageNum, Integer pageSize);
}
