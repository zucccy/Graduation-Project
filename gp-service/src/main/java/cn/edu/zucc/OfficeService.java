package cn.edu.zucc;

import cn.edu.zucc.dto.OfficeInfoDTO;
import cn.edu.zucc.po.Office;

import java.util.List;

/**
 * Description: 科室相关接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface OfficeService {

    /**
     * 根据科室编号查找科室信息
     *
     * @param id 科室编号
     * @return cn.edu.zucc.po.Office
     */
    Office findOfficeById(Long id);

    /**
     * 根据父编号查找科室列表
     *
     * @param parentId 父编号
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<Office> findOfficeList(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 根据科室名称查找科室列表
     *
     * @param officeName 科室名称
     * @param pageNum    第几页
     * @param pageSize   页大小
     * @return java.util.List<cn.edu.zucc.po.Office>
     */
    List<Office> findOfficeList(String officeName, Integer pageNum, Integer pageSize);

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
}
