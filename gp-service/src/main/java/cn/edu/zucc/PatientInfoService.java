package cn.edu.zucc;

import cn.edu.zucc.dto.PatientInfoDTO;
import cn.edu.zucc.dto.PatientInfoUpdateDTO;
import cn.edu.zucc.po.PatientInfo;
import cn.edu.zucc.vo.MyPatientVO;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
public interface PatientInfoService {

    /**
     * 根据身份证号查找患者信息
     *
     * @param idCard 身份证号
     * @return cn.edu.zucc.po.PatientInfo
     */
    PatientInfo findPatientInfoByIdCard(String idCard);


    /**
     * 为用户创建一个患者信息，添加用户患者关系
     *
     * @param userId         用户编号
     * @param patientInfoDTO 患者信息DTO
     * @return 是否成功
     */
    boolean insert(Long userId, PatientInfoDTO patientInfoDTO);


    /**
     * 更新用户的患者信息（姓名、手机号），更新用户患者关系
     *
     * @param userId                用户编号
     * @param patientId             患者编号
     * @param patientInfoUpadateDTO 患者信息修改参数
     * @return 是否成功
     */
    boolean update(Long userId, Long patientId, PatientInfoUpdateDTO patientInfoUpadateDTO);

    /**
     * 删除用户患者关系
     *
     * @param userId    用户编号
     * @param patientId 患者编号
     * @return boolean
     */
    boolean deleteRelation(Long userId, Long patientId);

    /**
     * 根据患者编号查找患者信息
     *
     * @param id 患者编号
     * @return cn.edu.zucc.po.PatientInfo
     */
    PatientInfo findPatientInfoById(Long id);

    /**
     * 查找患者列表信息
     *
     * @param patientName 患者名称
     * @param phone       手机号
     * @param sex         性别
     * @return java.util.List<cn.edu.zucc.po.PatientInfo>
     */
    List<PatientInfo> findPatientList(String patientName, String phone, Byte sex, Integer pageNum, Integer pageSize);

    /**
     * 通过用户编号查找患者列表信息
     *
     * @param userId 用户编号
     * @return java.util.List<cn.edu.zucc.po.PatientInfo>
     */
    List<MyPatientVO> findPatientList(Long userId);

    /**
     * 根据患者编号列表查找患者列表信息
     *
     * @param idList 患者编号列表
     * @return java.util.List<cn.edu.zucc.po.PatientInfo>
     */
    List<PatientInfo> findPatientList(List<Long> idList);


    /**
     * 判断用户患者关系是否存在
     *
     * @param userId    用户编号
     * @param patientId 患者编号
     * @return 是否存在
     */
    boolean countRelation(Long userId, Long patientId);

    /**
     * 根据身份证号判断患者信息是否存在
     *
     * @param idCard 身份证号
     * @return 是否存在
     */
    boolean countPatientInfo(String idCard);

    /**
     * 统计用户绑定的患者数量
     *
     * @param userId 用户编号
     * @return long
     */
    long count(Long userId);

    /**
     * 判断患者编号是否存在
     *
     * @param id 患者编号
     * @return boolean
     */
    boolean countPatientInfo(Long id);
}
