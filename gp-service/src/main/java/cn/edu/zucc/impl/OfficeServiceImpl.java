package cn.edu.zucc.impl;

import cn.edu.zucc.OfficeService;
import cn.edu.zucc.dto.OfficeInfoDTO;
import cn.edu.zucc.po.Office;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 科室相关接口实现类
 *
 * @author chenyun
 * @since 09.04.2021
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    @Override
    public Office findOfficeById(Long id) {
        return null;
    }

    @Override
    public List<Office> findOfficeList(Long parentId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<Office> findOfficeList(String officeName, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public boolean insert(OfficeInfoDTO officeInfoDTO) {
        return false;
    }

    @Override
    public boolean update(Long id, OfficeInfoDTO officeInfoDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean count(Long id) {
        return false;
    }

    @Override
    public List<Office> findOfficeListByHosId(Long hospitalId, Integer pageNum, Integer pageSize) {
        return null;
    }
}
