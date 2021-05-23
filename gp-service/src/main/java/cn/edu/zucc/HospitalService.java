package cn.edu.zucc;

import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.vo.AddressVO;
import cn.edu.zucc.vo.EssayVO;
import cn.edu.zucc.vo.HospitalInfoVO;
import cn.edu.zucc.vo.HospitalLocalationVO;
import cn.edu.zucc.vo.HospitalNewsVO;

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
     * 根据医院id查找医院所有信息
     *
     * @param id 医院编号
     * @return cn.edu.zucc.vo.HospitalInfoVO
     */
    HospitalInfoVO findHospitalVOById(Long id);

    /**
     * 分页搜索医院信息，可根据医院名、医院地址搜索
     *
     * @param hospitalName 医院名称
     * @param pageNum      第几页
     * @param pageSize     页大小
     * @return 医院列表
     */
    List<Hospital> findHospitalList(String hospitalName, Integer pageNum, Integer pageSize);

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
     * @param hospitalId 医院编号
     * @param officeId   科室编号
     * @return 是否成功
     */
    boolean insertOfficeRelation(Long hospitalId, Long officeId);

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
     * @param hospitalId 医院编号
     * @param officeId   科室编号
     * @return 是否存在
     */
    boolean countOfficeRelation(Long hospitalId, Long officeId);

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
     * 查找所有医院列表
     *
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.Hospital>
     */
    List<Hospital> getAllHospitals(Integer pageNum, Integer pageSize);

    /**
     * 根据医院编号列表查找医院信息列表
     *
     * @param hospitalIdList 医院编号列表
     * @return java.util.List<cn.edu.zucc.po.Hospital>
     */
    List<Hospital> findHospitalList(List<Long> hospitalIdList);

    /**
     * 获取排名在前9的医院资讯集合
     *
     * @return java.util.List<cn.edu.zucc.vo.HospitalNewsVO>
     */
    List<HospitalNewsVO> findHospitalNewsList();

    /**
     * 向Redis添加医院资讯
     *
     * @param hospitalNewsVO 医院资讯VO
     * @return boolean
     */
    boolean addHospitalNews(HospitalNewsVO hospitalNewsVO);

    /**
     * 提高redis中资讯名为title的分数
     *
     * @param hospitalNewsVO 医院资讯VO
     * @return boolean
     */
    boolean newsIncrementScore(HospitalNewsVO hospitalNewsVO);

    /**
     * 向Redis添加所有医院位置信息
     *
     * @param hospitalLocalationVO 医院位置信息VO
     * @return boolean
     */
    boolean addHospitalLocation(HospitalLocalationVO hospitalLocalationVO);

    /**
     * Redis中获取推荐医院集合
     *
     * @param addressVO 位置信息VO
     * @return java.util.List<cn.edu.zucc.vo.HospitalLocalationVO>
     */
    List<HospitalInfoVO> findAdviceHospitalList(AddressVO addressVO);

    /**
     * Redis中获取所有文章列表
     *
     * @return java.util.List<cn.edu.zucc.vo.EssayVO>
     */
    List<EssayVO> findEssayList();
}
