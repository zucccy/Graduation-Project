package cn.edu.zucc.impl;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.dto.HospitalOfficeRelDTO;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.HospitalMapper;
import cn.edu.zucc.mapper.HospitalRelOfficeMapper;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.HospitalExample;
import cn.edu.zucc.po.HospitalRelOffice;
import cn.edu.zucc.po.HospitalRelOfficeExample;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 医院相关接口实现类
 *
 * @author chenyun
 * @since 08.04.2021
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalMapper hospitalMapper;

    @Resource
    private HospitalRelOfficeMapper hospitalRelOfficeMapper;

    @Resource
    private OfficeService officeService;


    @Override
    public Hospital findHospitalById(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("医院不存在");
        }
        return hospitalMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Hospital> findHospitalList(String hospitalName, String address, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        HospitalExample example = new HospitalExample();
        HospitalExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isBlank(hospitalName)) {
            criteria.andHospitalNameLike("%" + hospitalName + "%");
        }
        if (!StringUtils.isBlank(address)) {
            criteria.andAddressLike("%" + address + "%");
        }
        return hospitalMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(HospitalInfoDTO hospitalInfoDTO) {
        Hospital hospital = new Hospital();

        //将DTO的值复制到hospital实体中
        BeanUtils.copyProperties(hospitalInfoDTO, hospital);

        hospital.setCreateTime(new Date());
        hospital.setUpdateTime(new Date());

        return hospitalMapper.insertSelective(hospital) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, HospitalInfoDTO hospitalInfoDTO) {
        Hospital hospital = new Hospital();

        BeanUtils.copyProperties(hospitalInfoDTO, hospital);

        hospital.setId(id);
        hospital.setUpdateTime(new Date());

        return hospitalMapper.updateByPrimaryKeySelective(hospital) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return hospitalMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean count(Long id) {
        HospitalExample example = new HospitalExample();

        example.createCriteria().andIdEqualTo(id);

        return hospitalMapper.selectCountByExample(example) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO) {

        HospitalRelOffice hospitalRelOffice = new HospitalRelOffice();
        //该医院存在
        if (!count(hospitalOfficeRelDTO.getHospitalId())) {
            throw new SourceNotFoundException("医院不存在");
        }
        //该科室存在
        if (officeService.count(hospitalOfficeRelDTO.getOfficeId())) {

            BeanUtils.copyProperties(hospitalOfficeRelDTO, hospitalRelOffice);
            hospitalRelOffice.setCreateTime(new Date());
            hospitalRelOffice.setUpdateTime(new Date());
            hospitalRelOfficeMapper.insertSelective(hospitalRelOffice);
            //若该科室有父科室，要把父科室也加入到关系中
            Long parentId = officeService.findOfficeById(hospitalOfficeRelDTO.getOfficeId()).getParentId();
            if (officeService.count(parentId)) {
                hospitalRelOffice.setOfficeId(parentId);
                hospitalRelOfficeMapper.insertSelective(hospitalRelOffice);
            }
        } else {
            throw new SourceNotFoundException("科室不存在");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOfficeRelation(Long hospitalId, Long officeId) {
        HospitalRelOfficeExample example = new HospitalRelOfficeExample();

        example.createCriteria().andHospitalIdEqualTo(hospitalId).andOfficeIdEqualTo(officeId);

        return hospitalRelOfficeMapper.deleteByExample(example) > 0;
    }

    @Override
    public boolean countOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO) {
        HospitalRelOfficeExample example = new HospitalRelOfficeExample();

        example.createCriteria().andHospitalIdEqualTo(hospitalOfficeRelDTO.getHospitalId())
                .andOfficeIdEqualTo(hospitalOfficeRelDTO.getOfficeId());

        return hospitalRelOfficeMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<Hospital> findHospitalList(Long officeId, Integer pageNum, Integer pageSize) {

        HospitalRelOfficeExample example = new HospitalRelOfficeExample();
        example.createCriteria().andOfficeIdEqualTo(officeId);

        //得到医院编号列表
        List<Long> hospitalIdList = hospitalRelOfficeMapper.selectByExample(example)
                .stream().map(HospitalRelOffice::getHospitalId).collect(Collectors.toList());
        //得到包含该科室的医院信息列表

        //分页查询放在最后一个select语句前
        PageHelper.startPage(pageNum, pageSize);

        return findHospitalList(hospitalIdList);
    }

    @Override
    public List<Hospital> findHospitalList(List<Long> hospitalIdList) {
        HospitalExample example = new HospitalExample();
        HospitalExample.Criteria criteria = example.createCriteria();

        if (CollectionUtil.isNotEmpty(hospitalIdList)) {
            criteria.andIdIn(hospitalIdList);
            return hospitalMapper.selectByExample(example);
        } else {
            return new ArrayList<>();
        }
    }
}
