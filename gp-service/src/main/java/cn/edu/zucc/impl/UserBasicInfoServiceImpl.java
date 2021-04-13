package cn.edu.zucc.impl;

import cn.edu.zucc.UserBasicInfoService;
import cn.edu.zucc.dto.UserBasicInfoDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.mapper.UserBasicInfoMapper;
import cn.edu.zucc.po.UserBasicInfo;
import cn.edu.zucc.po.UserBasicInfoExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
public class UserBasicInfoServiceImpl implements UserBasicInfoService {

    @Resource
    private UserBasicInfoMapper userBasicInfoMapper;

    @Override
    public boolean sendMessage(String phone) {
        return false;
    }

    @Override
    public boolean verifyCode(String phone, String code) {
        return false;
    }

    @Override
    public boolean insert(UserRegisterDTO userRegisterDTO) {
        UserBasicInfo userBasicInfo = new UserBasicInfo();

        BeanUtils.copyProperties(userRegisterDTO, userBasicInfo);

        userBasicInfo.setCreateTime(new Date());
        userBasicInfo.setUpdateTime(new Date());

        return userBasicInfoMapper.insertSelective(userBasicInfo) > 0;
    }

    @Override
    public boolean update(Long id, UserBasicInfoDTO userBasicInfoDTO) {
        UserBasicInfo userBasicInfo = new UserBasicInfo();

        BeanUtils.copyProperties(userBasicInfoDTO, userBasicInfo);

        userBasicInfo.setId(id);
        userBasicInfo.setUpdateTime(new Date());

        return userBasicInfoMapper.updateByPrimaryKeySelective(userBasicInfo) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return userBasicInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public UserBasicInfo findUserById(Long id) {
        return userBasicInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserBasicInfo findUserByPhone(String phone) {
        UserBasicInfoExample example = new UserBasicInfoExample();
        if (!StringUtils.isEmpty(phone)) {
            example.createCriteria().andPhoneEqualTo(phone);
        }
        return userBasicInfoMapper.selectOneByExample(example);
    }

    @Override
    public List<UserBasicInfo> findUserList(String userName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        UserBasicInfoExample example = new UserBasicInfoExample();
        UserBasicInfoExample.Criteria criteria = example.createCriteria();

        if (null != userName) {
            criteria.andUserNameLike("%" + userName + "%");
        }
        return userBasicInfoMapper.selectByExample(example);
    }

    @Override
    public boolean count(Long id) {
        UserBasicInfoExample example = new UserBasicInfoExample();
        example.createCriteria().andIdEqualTo(id);

        return userBasicInfoMapper.selectCountByExample(example) > 0;
    }

    @Override
    public boolean count(String phone) {
        UserBasicInfoExample example = new UserBasicInfoExample();
        example.createCriteria().andPhoneEqualTo(phone);

        return userBasicInfoMapper.selectCountByExample(example) > 0;
    }
}
