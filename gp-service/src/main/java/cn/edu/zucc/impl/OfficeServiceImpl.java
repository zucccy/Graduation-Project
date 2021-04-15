package cn.edu.zucc.impl;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.dto.OfficeInfoDTO;
import cn.edu.zucc.exception.SourceNotFoundException;
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
import java.util.ArrayList;
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

    @Resource
    private HospitalService hospitalService;

    @Override
    public Office findOfficeById(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("科室不存在");
        }
        return officeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Office> findOfficeList(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("该科室不存在");
        }

        OfficeExample example = new OfficeExample();
        example.createCriteria().andParentIdEqualTo(id);

        return officeMapper.selectByExample(example);
    }

    @Override
    public List<Office> findOfficeList(String officeName) {

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

        if (!hospitalService.count(hospitalId)) {
            throw new SourceNotFoundException("医院不存在");
        }

        HospitalRelOfficeExample example = new HospitalRelOfficeExample();
        example.createCriteria().andHospitalIdEqualTo(hospitalId);

        List<Long> officeIdList = hospitalRelOfficeMapper.selectByExample(example)
                .stream().map(HospitalRelOffice::getOfficeId).collect(Collectors.toList());

        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        return findOfficeList(officeIdList);
    }

    @Override
    public List<Office> findOfficeListAll() {
        return officeMapper.selectAll();
    }

    @Override
    public List<Office> findOfficeList(List<Long> officeIdList) {

        //用科室编号列表获取科室信息列表
        OfficeExample example = new OfficeExample();
        OfficeExample.Criteria criteria = example.createCriteria();

        if (CollectionUtil.isNotEmpty(officeIdList)) {
            criteria.andIdIn(officeIdList);
        } else {
            return new ArrayList<>();
        }
        return officeMapper.selectByExample(example);
    }
}
