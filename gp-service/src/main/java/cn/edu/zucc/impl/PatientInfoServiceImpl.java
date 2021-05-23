package cn.edu.zucc.impl;

import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.UserAccountInfoService;
import cn.edu.zucc.dto.PatientInfoDTO;
import cn.edu.zucc.dto.PatientInfoUpdateDTO;
import cn.edu.zucc.exception.ExistsException;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.PatientInfoMapper;
import cn.edu.zucc.mapper.UserRelPatientMapper;
import cn.edu.zucc.po.PatientInfo;
import cn.edu.zucc.po.PatientInfoExample;
import cn.edu.zucc.po.UserRelPatient;
import cn.edu.zucc.po.UserRelPatientExample;
import cn.edu.zucc.utils.CopyUtils;
import cn.edu.zucc.utils.FormatUtils;
import cn.edu.zucc.vo.MyPatientVO;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //用户不存在
        if (!userAccountInfoService.count(userId)) {
            throw new SourceNotFoundException("用户不存在");
        }
        //若患者已存在（验证身份证号），则插入关系
        if (countPatientInfo(patientInfoDTO.getIdCard())) {
            PatientInfo patientInfo = findPatientInfoByIdCard(patientInfoDTO.getIdCard());
            if (countRelation(userId, patientInfo.getId())) {
                throw new ExistsException("就诊人已存在");
            }
            UserRelPatient userRelPatient = new UserRelPatient();

            userRelPatient.setUserId(userId);
            userRelPatient.setPatientId(patientInfo.getId());
            userRelPatient.setCreateTime(new Date());
            userRelPatient.setUpdateTime(new Date());

            return userRelPatientMapper.insertSelective(userRelPatient) > 0;
        }
        //若患者不存在
        PatientInfo patientInfo = new PatientInfo();

        BeanUtils.copyProperties(patientInfoDTO, patientInfo);
        patientInfo.setCreateTime(new Date());
        patientInfo.setUpdateTime(new Date());

        //校验身份证号
        if (FormatUtils.isIdCard(patientInfoDTO.getIdCard())) {
            patientInfo.setIdCard(patientInfoDTO.getIdCard());
        } else {
            throw new FormException();
        }
        //校验手机号
        if (FormatUtils.isPhoneNumber(patientInfoDTO.getPhone())) {
            patientInfo.setPhone(patientInfoDTO.getPhone());
        } else {
            throw new FormException();
        }
        //若该患者插入成功
        if (patientInfoMapper.insertSelective(patientInfo) > 0) {
            UserRelPatient userRelPatient = new UserRelPatient();
            userRelPatient.setUserId(userId);
            userRelPatient.setPatientId(patientInfo.getId());
            userRelPatient.setCreateTime(new Date());
            userRelPatient.setUpdateTime(new Date());
            return userRelPatientMapper.insertSelective(userRelPatient) > 0;
        }
        //插入不成功
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long userId, Long patientId, PatientInfoUpdateDTO patientInfoUpdateDTO) {

        //判断患者名以及患者手机号是否为空
        if (StringUtils.isBlank(patientInfoUpdateDTO.getPatientName()) || StringUtils.isBlank(patientInfoUpdateDTO.getPhone())) {
            throw new FormException();
        }
        //关系存在
        if (!countRelation(userId, patientId)) {
            throw new SourceNotFoundException("该用户患者关系不存在");
        }
        if (!userAccountInfoService.count(userId)) {
            throw new SourceNotFoundException("用户不存在");
        }
        if (!countPatientInfo(patientId)) {
            throw new SourceNotFoundException("患者不存在");
        }
        //更新患者信息
        PatientInfo patientInfo = findPatientInfoById(patientId);
        patientInfo.setPatientName(patientInfoUpdateDTO.getPatientName());
        patientInfo.setUpdateTime(new Date());
        //校验手机号
        if (FormatUtils.isPhoneNumber(patientInfoUpdateDTO.getPhone())) {
            patientInfo.setPhone(patientInfoUpdateDTO.getPhone());
        } else {
            throw new FormException();
        }

        return patientInfoMapper.updateByPrimaryKeySelective(patientInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRelation(Long userId, Long patientId) {
        UserRelPatientExample example = new UserRelPatientExample();
        UserRelPatientExample.Criteria criteria = example.createCriteria();

        if (!countRelation(userId, patientId)) {
            throw new SourceNotFoundException("该用户与患者关系不存在");
        }
        criteria.andUserIdEqualTo(userId).andPatientIdEqualTo(patientId);
        return userRelPatientMapper.deleteByExample(example) > 0;
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
    public List<MyPatientVO> findPatientList(Long userId) {
        UserRelPatientExample example = new UserRelPatientExample();
        UserRelPatientExample.Criteria criteria = example.createCriteria();

        if (null != userId) {
            criteria.andUserIdEqualTo(userId);
        }
        //通过患者id列表去查找患者信息列表
        List<MyPatientVO> myPatientVOList = CopyUtils.copyList(findPatientList(userRelPatientMapper.selectByExample(example)
                .stream().map(UserRelPatient::getPatientId).collect(Collectors.toList())), MyPatientVO.class);
        return myPatientVOList;
    }

    @Override
    public List<PatientInfo> findPatientList(List<Long> idList) {
        PatientInfoExample example = new PatientInfoExample();
        example.createCriteria().andIdIn(idList);
        return patientInfoMapper.selectByExample(example);
    }

    @Override
    public boolean countRelation(Long userId, Long patientId) {
        UserRelPatientExample example = new UserRelPatientExample();

        if (null == userId || null == patientId) {
            throw new FormException();
        }
        example.createCriteria().andUserIdEqualTo(userId).andPatientIdEqualTo(patientId);
        return userRelPatientMapper.selectCountByExample(example) > 0;
    }

    @Override
    public boolean countRelation(Long userId) {
        UserRelPatientExample example = new UserRelPatientExample();

        if (null == userId) {
            throw new FormException();
        }
        example.createCriteria().andUserIdEqualTo(userId);
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
