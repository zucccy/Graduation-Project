package cn.edu.zucc.impl;

import cn.edu.zucc.DoctorService;
import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.mapper.DoctorInfoMapper;
import cn.edu.zucc.po.DoctorInfo;
import cn.edu.zucc.po.DoctorInfoExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    DoctorInfoMapper doctorInfoMapper;

    @Override
    public DoctorInfo findDoctorById(Long id) {
        return doctorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DoctorInfo> findDoctorList(String doctorName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        DoctorInfoExample example = new DoctorInfoExample();
        DoctorInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(doctorName)) {
            criteria.andDoctorNameLike("%" + doctorName + "%");
        }

        return doctorInfoMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = new DoctorInfo();

        BeanUtils.copyProperties(doctorInfoDTO, doctorInfo);

        doctorInfo.setCreateTime(new Date());
        doctorInfo.setUpdateTime(new Date());

        return doctorInfoMapper.insertSelective(doctorInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = new DoctorInfo();

        BeanUtils.copyProperties(doctorInfoDTO, doctorInfo);

        doctorInfo.setId(id);
        doctorInfo.setUpdateTime(new Date());

        return doctorInfoMapper.updateByPrimaryKeySelective(doctorInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return doctorInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean count(Long id) {
        DoctorInfoExample example = new DoctorInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return doctorInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<DoctorInfo> findDoctorList(Long officeId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        DoctorInfoExample example = new DoctorInfoExample();
        DoctorInfoExample.Criteria criteria = example.createCriteria();

        if (null != officeId) {
            criteria.andOfficeIdEqualTo(officeId);
        }

        return doctorInfoMapper.selectByExample(example);
    }

    @Override
    public List<DoctorInfo> findDoctorListByHosId(Long hospitalId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        DoctorInfoExample example = new DoctorInfoExample();
        DoctorInfoExample.Criteria criteria = example.createCriteria();

        if (null != hospitalId) {
            criteria.andHospitalIdEqualTo(hospitalId);
        }

        return doctorInfoMapper.selectByExample(example);
    }
}
