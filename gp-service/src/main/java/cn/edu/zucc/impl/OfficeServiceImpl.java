package cn.edu.zucc.impl;

import cn.edu.zucc.OfficeService;
import cn.edu.zucc.dto.OfficeInfoDTO;
import cn.edu.zucc.mapper.HospitalRelOfficeMapper;
import cn.edu.zucc.mapper.OfficeMapper;
import cn.edu.zucc.po.HospitalRelOffice;
import cn.edu.zucc.po.HospitalRelOfficeExample;
import cn.edu.zucc.po.Office;
import cn.edu.zucc.po.OfficeExample;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 科室相关接口实现类
 *
 * @author chenyun
 * @since 09.04.2021
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    @Resource
    private OfficeMapper officeMapper;

    @Resource
    private HospitalRelOfficeMapper hospitalRelOfficeMapper;

    @Override
    public Office findOfficeById(Long id) {
        return officeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Office> findOfficeList(Long parentId, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        OfficeExample example = new OfficeExample();
        OfficeExample.Criteria criteria = example.createCriteria();

        if (null != parentId) {
            criteria.andParentIdEqualTo(parentId);
        }

        return officeMapper.selectByExample(example);
    }

    @Override
    public List<Office> findOfficeList(String officeName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        OfficeExample example = new OfficeExample();
        OfficeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(officeName)) {
            criteria.andOfficeNameLike("%" + officeName + "%");
        }

        return officeMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(OfficeInfoDTO officeInfoDTO) {
        Office office = new Office();

        BeanUtils.copyProperties(officeInfoDTO, office);

        office.setCreateTime(new Date());
        office.setUpdateTime(new Date());

        return officeMapper.insertSelective(office) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, OfficeInfoDTO officeInfoDTO) {
        Office office = new Office();

        BeanUtils.copyProperties(officeInfoDTO, office);

        office.setId(id);
        office.setUpdateTime(new Date());

        return officeMapper.updateByPrimaryKeySelective(office) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return officeMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean count(Long id) {
        OfficeExample example = new OfficeExample();
        example.createCriteria().andIdEqualTo(id);

        return officeMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<Office> findOfficeListByHosId(Long hospitalId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        HospitalRelOfficeExample example = new HospitalRelOfficeExample();
        HospitalRelOfficeExample.Criteria criteria = example.createCriteria();

        if (null != hospitalId) {
            criteria.andHospitalIdEqualTo(hospitalId);
        }
        //根据医院编号获取科室编号列表
        List<Long> officeIdList = hospitalRelOfficeMapper.selectByExample(example)
                .stream().map(HospitalRelOffice::getOfficeId).collect(Collectors.toList());

        //用科室编号列表获取科室信息列表
        OfficeExample example1 = new OfficeExample();
        OfficeExample.Criteria criteria1 = example1.createCriteria();

        if (CollectionUtil.isNotEmpty(officeIdList)) {
            criteria1.andIdIn(officeIdList);
        }

        return officeMapper.selectByExample(example1);
    }
}
