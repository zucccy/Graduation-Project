package cn.edu.zucc.impl;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.DoctorService;
import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.UserAccountInfoService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.dto.UpdatePasswordDTO;
import cn.edu.zucc.dto.UserAccountInfoUpdateDTO;
import cn.edu.zucc.dto.UserLoginDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.enums.RoleTypeEnum;
import cn.edu.zucc.exception.ExistsException;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.exception.WrongPasswordException;
import cn.edu.zucc.mapper.UserAccountInfoMapper;
import cn.edu.zucc.po.AppointmentInfo;
import cn.edu.zucc.po.UserAccountInfo;
import cn.edu.zucc.po.UserAccountInfoExample;
import cn.edu.zucc.utils.CryptUtils;
import cn.edu.zucc.utils.FormatUtils;
import cn.edu.zucc.vo.MyAppointmentListVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class UserAccountInfoServiceImpl implements UserAccountInfoService {

    @Resource
    private UserAccountInfoMapper userAccountInfoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AppointmentInfoService appointmentInfoService;

    @Resource
    private VisitPlanService visitPlanService;

    @Resource
    private HospitalService hospitalService;

    @Resource
    private OfficeService officeService;

    @Resource
    private PatientInfoService patientInfoService;

    @Resource
    private DoctorService doctorService;

    @Override
    //登录
    public UserAccountInfo login(UserLoginDTO userLoginDTO) {
        //先查找用户看是否存在
        UserAccountInfo userAccountInfo = findUserByOpenCode(userLoginDTO.getOpenCode());
        //如果选择了医生身份登录，则要判断是否是医生
        if (RoleTypeEnum.DOCTOR.getTypes().byteValue() == userLoginDTO.getRoleType()) {
            if (RoleTypeEnum.DOCTOR.getTypes().byteValue() != userAccountInfo.getRoleType()) {
                throw new SourceNotFoundException("不存在该医生");
            }
            return userAccountInfo;
        }
        //非医生身份，默认为用户身份，角色类型赋值为用户
        userAccountInfo.setRoleType(RoleTypeEnum.USER.getTypes().byteValue());
        if (!CryptUtils.matchAccountPassword(userAccountInfo.getPassword(), userLoginDTO.getPassword())) {
            throw new WrongPasswordException();
        }
        return userAccountInfo;
    }

    @Override
    //注册
    @Transactional(rollbackFor = Exception.class)
    public boolean register(UserRegisterDTO userRegisterDTO) {
        UserAccountInfo userAccountInfo = new UserAccountInfo();

        BeanUtils.copyProperties(userRegisterDTO, userAccountInfo);

        String openCode = userRegisterDTO.getOpenCode();
        //邮箱
        if (FormatUtils.isEmail(openCode)) {
            //若已存在
            if (countByEmail(openCode)) {
                throw new ExistsException();
            }
            userAccountInfo.setEmail(openCode);
        }
        //手机号
        else if (FormatUtils.isPhoneNumber(openCode)) {
            //若已存在
            if (countByPhone(openCode)) {
                throw new ExistsException();
            }
            userAccountInfo.setPhone(openCode);
        } else {
            throw new FormException();
        }
        //密码加密
        userAccountInfo.setPassword(CryptUtils.cryptAccountPassword(userRegisterDTO.getPassword()));
        userAccountInfo.setCreateTime(new Date());
        userAccountInfo.setUpdateTime(new Date());

        return userAccountInfoMapper.insertSelective(userAccountInfo) > 0;
    }

    @Override
    //修改个人信息
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, UserAccountInfoUpdateDTO userAccountInfoUpdateDTO) {
        UserAccountInfo userAccountInfo = findUserById(id);
        //用户未找到
        if (null == userAccountInfo) {
            throw new SourceNotFoundException("用户不存在");
        }

        userAccountInfo.setUserName(userAccountInfoUpdateDTO.getUserName());
        userAccountInfo.setUpdateTime(new Date());

        //手机号不为空，修改手机号
        String phone = userAccountInfoUpdateDTO.getPhone();
        if (!StringUtils.isBlank(phone)) {
            if (FormatUtils.isPhoneNumber(phone)) {
                userAccountInfo.setPhone(phone);
            } else {
                throw new FormException();
            }
        }
        //邮箱不为空，修改邮箱
        String email = userAccountInfoUpdateDTO.getEmail();
        if (!StringUtils.isBlank(email)) {
            if (FormatUtils.isEmail(email)) {
                userAccountInfo.setEmail(email);
            } else {
                throw new FormException();
            }
        }

        return userAccountInfoMapper.updateByPrimaryKeySelective(userAccountInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        if (null != id) {
            return userAccountInfoMapper.deleteByPrimaryKey(id) > 0;
        } else {
            throw new FormException();
        }
    }

    @Override
    public UserAccountInfo findUserById(Long id) {
        if (null != id) {
            return userAccountInfoMapper.selectByPrimaryKey(id);
        } else {
            throw new FormException();
        }
    }

    @Override
    public UserAccountInfo findUserByOpenCode(String openCode) {
        UserAccountInfoExample example = new UserAccountInfoExample();

        if (!StringUtils.isBlank(openCode)) {
            //若为手机号
            if (FormatUtils.isPhoneNumber(openCode)) {
                example.createCriteria().andPhoneEqualTo(openCode);
            }
            //若为邮箱
            else if (FormatUtils.isEmail(openCode)) {
                example.createCriteria().andEmailEqualTo(openCode);
            }
        }
        UserAccountInfo userAccountInfo = userAccountInfoMapper.selectOneByExample(example);
        if (null == userAccountInfo) {
            throw new SourceNotFoundException("用户不存在");
        }
        return userAccountInfo;
    }

    @Override
    public List<UserAccountInfo> findUserList(String userName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        UserAccountInfoExample example = new UserAccountInfoExample();
        UserAccountInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isBlank(userName)) {
            criteria.andUserNameLike("%" + userName + "%");
        }
        return userAccountInfoMapper.selectByExample(example);
    }

    @Override
    public boolean count(Long id) {
        UserAccountInfoExample example = new UserAccountInfoExample();
        if (null != id) {
            example.createCriteria().andIdEqualTo(id);
        } else {
            throw new FormException();
        }
        return userAccountInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public boolean countByPhone(String phone) {
        UserAccountInfoExample example = new UserAccountInfoExample();
        if (!StringUtils.isBlank(phone)) {
            example.createCriteria().andPhoneEqualTo(phone);
        } else {
            throw new FormException();
        }
        return userAccountInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public boolean countByEmail(String email) {
        UserAccountInfoExample example = new UserAccountInfoExample();
        if (!StringUtils.isBlank(email)) {
            example.createCriteria().andEmailEqualTo(email);
        } else {
            throw new FormException();
        }
        return userAccountInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, UpdatePasswordDTO updatePasswordDTO) {
        UserAccountInfo userAccountInfo = findUserById(id);
        //用户未找到
        if (null == userAccountInfo) {
            throw new SourceNotFoundException("用户不存在");
        }
        //判断当前密码是否正确，正确则修改密码
        if (CryptUtils.matchAccountPassword(userAccountInfo.getPassword(), updatePasswordDTO.getRowPassword())) {
            //判断新密码是否为空
            if (!StringUtils.isBlank(updatePasswordDTO.getNewPassword())) {
                userAccountInfo.setPassword(CryptUtils.cryptAccountPassword(updatePasswordDTO.getNewPassword()));
                userAccountInfoMapper.updateByPrimaryKeySelective(userAccountInfo);
            } else {
                throw new FormException();
            }
        } else {
            throw new WrongPasswordException();
        }
    }

    @Override
    public List<MyAppointmentListVO> getMyAppointments(Long id) {

        List<MyAppointmentListVO> list = new ArrayList<>();
        //redis里查找用户预约列表
        String pattern = id + ":*";
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (!CollectionUtil.isEmpty(keys)) {
            for (String accurateKey : keys) {
                String cacheValue = (String) redisTemplate.opsForValue().get(accurateKey);
                MyAppointmentListVO myAppointmentListVO = JSON.parseObject(cacheValue, MyAppointmentListVO.class);
                list.add(myAppointmentListVO);
            }
        }
        //redis里找不到，则去mysql里找，找到之后存到redis中
        else {
            appointmentInfoService.findAppointmentListByUserId(id, null, 1, 10)
                    .forEach((AppointmentInfo appointmentInfo) -> {
                        //获取我的预约对象
                        MyAppointmentListVO myAppointmentListVO = appointmentInfoService.convertMyAppointmentListVO(appointmentInfo);
                        list.add(myAppointmentListVO);
                        //存到redis中
                        //将对象转换为Json字符串
                        String myAppointmentVOListJson = JSONUtil.toJsonStr(myAppointmentListVO);
                        //key = 用户编号:预约编号
                        String key = appointmentInfo.getUserId() + ":" + appointmentInfo.getId();
                        redisTemplate.opsForValue().set(key, myAppointmentVOListJson);
                    });
        }
        return list;
    }

}
