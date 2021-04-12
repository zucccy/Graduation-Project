package cn.edu.zucc.impl;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.dto.HospitalOfficeRelDTO;
import cn.edu.zucc.mapper.HospitalMapper;
import cn.edu.zucc.mapper.HospitalRelOfficeMapper;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.HospitalExample;
import cn.edu.zucc.po.HospitalRelOffice;
import cn.edu.zucc.po.HospitalRelOfficeExample;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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


    @Override
    public Hospital findHospitalById(Long id) {
        HospitalExample example = new HospitalExample();
        example.createCriteria().andIdEqualTo(id);
        return hospitalMapper.selectOneByExample(example);
    }

    @Override
    public List<Hospital> findHospitalList(String hospitalName, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        HospitalExample example = new HospitalExample();
        if (!StringUtils.isEmpty(hospitalName)) {
            example.createCriteria().andHospitalNameLike("%" + hospitalName + "%");
        }

        return hospitalMapper.selectByExample(example);
    }

    @Override
    public boolean insert(HospitalInfoDTO hospitalInfoDTO) {
        Hospital hospital = new Hospital();

        //将DTO的值复制到hospital实体中
        BeanUtils.copyProperties(hospitalInfoDTO, hospital);

        hospital.setCreateTime(new Date());
        hospital.setUpdateTime(new Date());

        return hospitalMapper.insertSelective(hospital) > 0;
    }

    @Override
    public boolean update(Long id, HospitalInfoDTO hospitalInfoDTO) {
        Hospital hospital = new Hospital();

        BeanUtils.copyProperties(hospitalInfoDTO, hospital);

        hospital.setId(id);
        hospital.setUpdateTime(new Date());

        return hospitalMapper.updateByPrimaryKeySelective(hospital) > 0;
    }

    @Override
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
    public boolean insertOfficeRelation(HospitalOfficeRelDTO hospitalOfficeRelDTO) {
        HospitalRelOffice hospitalRelOffice = new HospitalRelOffice();

        BeanUtils.copyProperties(hospitalOfficeRelDTO, hospitalRelOffice);

        hospitalRelOffice.setCreateTime(new Date());
        hospitalRelOffice.setUpdateTime(new Date());

        return hospitalRelOfficeMapper.insertSelective(hospitalRelOffice) > 0;
    }

    @Override
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
        PageHelper.startPage(pageNum, pageSize);

        HospitalRelOfficeExample example = new HospitalRelOfficeExample();

        HospitalRelOfficeExample.Criteria criteria = example.createCriteria();
        if (null != officeId) {
            criteria.andOfficeIdEqualTo(officeId);
        }
        //得到医院编号列表
        List<Long> hospitalIdList = hospitalRelOfficeMapper.selectByExample(officeId)
                .stream().map(HospitalRelOffice::getHospitalId).collect(Collectors.toList());
        //得到包含该科室的医院列表
        HospitalExample example1 = new HospitalExample();

        HospitalExample.Criteria criteria1 = example1.createCriteria();

        if (CollectionUtil.isNotEmpty(hospitalIdList)) {
            criteria1.andIdIn(hospitalIdList);
        }

        return hospitalMapper.selectByExample(example1);
    }
}
