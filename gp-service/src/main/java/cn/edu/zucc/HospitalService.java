package cn.edu.zucc;

import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.dto.HospitalOfficeRelDTO;
import cn.edu.zucc.po.Hospital;

import java.util.List;

/**
 * Description: 医院相关接口
 *
 * @author chenyun
 * @since 08.04.2021
 */
public interface HospitalService {

    /**
     * 根据医院id查找医院
     *
     * @param id 医院编号
     * @return 单个医院信息
     */
    Hospital findHospitalById(Long id);

    /**
     * 分页搜索医院信息，可根据医院名、医院地址搜索
     *
     * @param hospitalName 医院名称
     * @param address      医院地址
     * @param pageNum      第几页
     * @param pageSize     页大小
     * @return 医院列表
     */
    List<Hospital> findHospitalList(String hospitalName, String address, Integer pageNum, Integer pageSize);

    /**
     * 添加医院信息
     *
     * @param hospitalInfoDTO 医院信息DTO
     * @return 是否成功
     */
    boolean insert(HospitalInfoDTO hospitalInfoDTO);

    /**
     * 更新医院信息
     *
     * @param id              医院编号
     * @param hospitalInfoDTO 医院信息DTO
     * @return 是否成功
     */
    boolean update(Long id, HospitalInfoDTO hospitalInfoDTO);

    /**
     * 删除医院信息
     *
     * @param id 医院编号
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 判断医院信息是否存在
     *
     * @param id 医院编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 添加科室到医院中
     *
     * @param hospitalOfficeRelDTO 医院科室关系DTO
     * @return 是否成功
     */
    boolean insertOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO);

    /**
     * 在医院中删除科室//判断子科室
     *
     * @param hospitalId 医院编号
     * @param officeId   科室编号
     * @return 是否成功
     */
    boolean deleteOfficeRelation(Long hospitalId, Long officeId);

    /**
     * 判断科室在医院中是否存在
     *
     * @param hospitalOfficeRelDTO 医院科室关系DTO
     * @return 是否存在
     */
    boolean countOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO);

    /**
     * 根据科室编号查找医院列表
     *
     * @param officeId 科室编号
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.Hospital>
     */
    List<Hospital> findHospitalList(Long officeId, Integer pageNum, Integer pageSize);

    /**
     * 根据医院编号列表查找医院信息列表
     *
     * @param hospitalIdList 医院编号列表
     * @return java.util.List<cn.edu.zucc.po.Hospital>
     */
    List<Hospital> findHospitalList(List<Long> hospitalIdList);
}
