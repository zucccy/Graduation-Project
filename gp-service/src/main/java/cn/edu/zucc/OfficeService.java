package cn.edu.zucc;

import cn.edu.zucc.dto.OfficeInfoDTO;
import cn.edu.zucc.po.Office;
import cn.edu.zucc.vo.ChildOfficeVO;
import cn.edu.zucc.vo.OfficeVO;

import java.util.List;

/**
 * Description: 科室相关接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface OfficeService {

    List<Office> getAllOffices(String officeName, Integer pageNum, Integer pageSize);

    /**
     * 根据科室编号查找科室信息
     *
     * @param id 科室编号
     * @return cn.edu.zucc.po.Office
     */
    Office findOfficeById(Long id);

    /**
     * 根据科室编号查找子科室列表
     *
     * @param id 科室编号
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<ChildOfficeVO> findChildOfficeList(Long id);

    /**
     * 根据科室编号获取科室集合
     *
     * @param hospitalId 医院编号
     * @return java.util.List<cn.edu.zucc.vo.OfficeVO>
     */
    List<OfficeVO> findOfficeVOList(Long hospitalId);

    /**
     * 判断科室是否有子科室
     *
     * @param id 科室编号
     * @return 是否存在
     */
    boolean countChildOffice(Long id);

    /**
     * 根据科室名称查找科室列表
     *
     * @param officeName 科室名称
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<Office> findOfficeList(String officeName);

    /**
     * 添加科室
     *
     * @param officeInfoDTO 科室信息参数
     * @return 是否成功
     */
    boolean insert(OfficeInfoDTO officeInfoDTO);

    /**
     * 更新科室
     *
     * @param id            科室编号
     * @param officeInfoDTO 科室信息参数
     * @return 是否成功
     */
    boolean update(Long id, OfficeInfoDTO officeInfoDTO);

    /**
     * 删除科室
     *
     * @param id 科室编号
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 级联删除所有子科室
     *
     * @param id 科室编号
     * @return boolean
     */
    boolean deleteChild(Long id);

    /**
     * 判断科室信息是否存在
     *
     * @param id 科室编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 查找医院中所有科室信息
     *
     * @param hospitalId 医院编号
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<Office> findOfficeListByHosId(Long hospitalId, Integer pageNum, Integer pageSize);

    /**
     * 查找所有科室信息
     *
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<OfficeVO> findOfficeListAll();

    /**
     * 根据科室编号列表查找科室信息列表
     *
     * @param officeIdList 科室编号列表
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<Office> findOfficeList(List<Long> officeIdList);


}
