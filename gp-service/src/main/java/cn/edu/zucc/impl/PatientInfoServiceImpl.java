package cn.edu.zucc.impl;

import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.UserAccountInfoService;
import cn.edu.zucc.dto.PatientInfoDTO;
import cn.edu.zucc.dto.PatientInfoUpadateDTO;
import cn.edu.zucc.mapper.PatientInfoMapper;
import cn.edu.zucc.mapper.UserRelPatientMapper;
import cn.edu.zucc.po.PatientInfo;
import cn.edu.zucc.po.PatientInfoExample;
import cn.edu.zucc.po.UserRelPatient;
import cn.edu.zucc.po.UserRelPatientExample;
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
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    public static final int MAN = 1;

    public static final int WOMAN = 0;

    @Resource
    private PatientInfoMapper patientInfoMapper;

    @Resource
    private UserRelPatientMapper userRelPatientMapper;

    @Resource
    private UserAccountInfoService userAccountInfoService;

    @Override
    public PatientInfo findPatientInfoByIdCard(String idCard) {
        PatientInfoExample example = new PatientInfoExample();
        PatientInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(idCard)) {
            criteria.andIdCardEqualTo(idCard);
        }
        return patientInfoMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    //增删改操作添加@Transactional
    public boolean insert(Long userId, PatientInfoDTO patientInfoDTO) {
        //若患者已存在（验证身份证号），则插入关系
        if (countPatientInfo(patientInfoDTO.getIdCard())) {
            PatientInfo patientInfo = findPatientInfoByIdCard(patientInfoDTO.getIdCard());

            UserRelPatient userRelPatient = new UserRelPatient();
            if (null != userId) {
                userRelPatient.setUserId(userId);
            }
            userRelPatient.setPatientId(patientInfo.getId());

            return userRelPatientMapper.insertSelective(userRelPatient) > 0;
        }
        //若患者不存在
        PatientInfo patientInfo = new PatientInfo();

        BeanUtils.copyProperties(patientInfoDTO, patientInfo);
        patientInfo.setCreateTime(new Date());
        patientInfo.setUpdateTime(new Date());
        //若该患者插入成功
        if (patientInfoMapper.insertSelective(patientInfo) > 0) {
            UserRelPatient userRelPatient = new UserRelPatient();
            if (null != userId) {
                userRelPatient.setUserId(userId);
            }
            //insert能不能返回id待定
            userRelPatient.setPatientId(patientInfo.getId());

            return userRelPatientMapper.insertSelective(userRelPatient) > 0;
        }
        //插入不成功
        return false;
    }

    @Override
    public boolean update(Long relationId, PatientInfoUpadateDTO patientInfoUpadateDTO) {

        int count = 0;

        //更新关系
        if (!StringUtils.isEmpty(patientInfoUpadateDTO.getPatientName()) && !StringUtils.isEmpty(patientInfoUpadateDTO.getPhone())) {
            UserRelPatient userRelPatient = new UserRelPatient();
            BeanUtils.copyProperties(patientInfoUpadateDTO, userRelPatient);

            userRelPatient.setId(relationId);
            userRelPatient.setUpdateTime(new Date());

            count = userRelPatientMapper.updateByPrimaryKeySelective(userRelPatient);
        }

        //更新患者信息
        PatientInfo patientInfo = new PatientInfo();
        BeanUtils.copyProperties(patientInfoUpadateDTO, patientInfo);

        patientInfo.setId(userRelPatientMapper.selectByPrimaryKey(relationId).getPatientId());
        patientInfo.setUpdateTime(new Date());

        count += patientInfoMapper.updateByPrimaryKeySelective(patientInfo);

        return count > 0;
    }

    @Override
    public boolean deleteRelation(Long relationId) {
        return userRelPatientMapper.deleteByPrimaryKey(relationId) > 0;
    }

    @Override
    public PatientInfo findPatientInfoById(Long id) {
        return patientInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PatientInfo> findPatientList(String patientName, String phone, Byte sex, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        PatientInfoExample example = new PatientInfoExample();
        PatientInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(patientName)) {
            criteria.andPatientNameLike("%" + patientName + "%");
        }
        if (!StringUtils.isEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }
        if (MAN == sex || WOMAN == sex) {
            criteria.andSexEqualTo(sex);
        }
        return patientInfoMapper.selectByExample(example);
    }

    @Override
    public List<PatientInfo> findPatientList(Long userId) {
        UserRelPatientExample example = new UserRelPatientExample();
        UserRelPatientExample.Criteria criteria = example.createCriteria();

        if (null != userId) {
            criteria.andUserIdEqualTo(userId);
        }
        //通过患者id列表去查找患者信息列表
        return findPatientList(userRelPatientMapper.selectByExample(example)
                .stream().map(UserRelPatient::getPatientId).collect(Collectors.toList()));
    }

    @Override
    public List<PatientInfo> findPatientList(List<Long> idList) {
        PatientInfoExample example = new PatientInfoExample();
        example.createCriteria().andIdIn(idList);
        return patientInfoMapper.selectByExample(example);
    }

    @Override
    public boolean countRelation(Long relationId) {
        UserRelPatientExample example = new UserRelPatientExample();
        example.createCriteria().andIdEqualTo(relationId);
        return userRelPatientMapper.selectCountByExample(example) > 0;
    }

    @Override
    public boolean countPatientInfo(String idCard) {
        PatientInfoExample example = new PatientInfoExample();

        if (!StringUtils.isEmpty(idCard)) {
            example.createCriteria().andIdCardEqualTo(idCard);
        }
        return patientInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public long count(Long userId) {
        UserRelPatientExample example = new UserRelPatientExample();

        if (null != userId) {
            example.createCriteria().andUserIdEqualTo(userId);
        }
        return userRelPatientMapper.selectCountByExample(example);
    }

    @Override
    public boolean countPatientInfo(Long id) {
        PatientInfoExample example = new PatientInfoExample();

        if (null != id) {
            example.createCriteria().andIdEqualTo(id);
        }
        return patientInfoMapper.selectCountByExample(example) > 0;
    }
}
