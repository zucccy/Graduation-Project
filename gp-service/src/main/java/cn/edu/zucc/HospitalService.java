package cn.edu.zucc;

import cn.edu.zucc.po.Hospital;

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
     * @param id
     * @return cn.edu.zucc.po.Hospital
     */
    Hospital findHospitalById(Long id);
}
