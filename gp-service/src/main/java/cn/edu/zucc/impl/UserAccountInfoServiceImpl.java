package cn.edu.zucc.impl;

import cn.edu.zucc.UserAccountInfoService;
import cn.edu.zucc.dto.UpdatePasswordDTO;
import cn.edu.zucc.dto.UserAccountInfoUpdateDTO;
import cn.edu.zucc.dto.UserLoginDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.enums.ResultCodeEnum;
import cn.edu.zucc.exception.ExistsException;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.exception.WrongPasswordException;
import cn.edu.zucc.mapper.UserAccountInfoMapper;
import cn.edu.zucc.po.UserAccountInfo;
import cn.edu.zucc.po.UserAccountInfoExample;
import cn.edu.zucc.utils.CryptUtils;
import cn.edu.zucc.utils.FormatUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class UserAccountInfoServiceImpl implements UserAccountInfoService {

    @Resource
    private UserAccountInfoMapper userAccountInfoMapper;

    @Override
    //登录
    public UserAccountInfo login(UserLoginDTO userLoginDTO) {
        UserAccountInfo userAccountInfo = findUserByOpenCode(userLoginDTO.getOpenCode());
        if (!CryptUtils.matchAccountPassword(userAccountInfo.getPassword(), userLoginDTO.getPassword())) {
            throw new WrongPasswordException();
        }
        return userAccountInfo;
    }

    @Override
    //注册
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
    public boolean update(Long id, UserAccountInfoUpdateDTO userAccountInfoUpdateDTO) {
        UserAccountInfo userAccountInfo = findUserById(id);
        //用户未找到
        if (null == userAccountInfo) {
            throw new SourceNotFoundException(ResultCodeEnum.NOT_FOUND.getMessage());
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
            throw new SourceNotFoundException(ResultCodeEnum.NOT_FOUND.getMessage());
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
    public void updatePassword(Long id, UpdatePasswordDTO updatePasswordDTO) {
        UserAccountInfo userAccountInfo = findUserById(id);
        //用户未找到
        if (null == userAccountInfo) {
            throw new SourceNotFoundException(ResultCodeEnum.NOT_FOUND.getMessage());
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
}
