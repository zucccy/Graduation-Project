package cn.edu.zucc.impl;

import cn.edu.zucc.UserBasicInfoService;
import cn.edu.zucc.dto.UserBasicInfoDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.po.UserBasicInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Service
public class UserBasicInfoServiceImpl implements UserBasicInfoService {
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
        return false;
    }

    @Override
    public boolean update(Long id, UserBasicInfoDTO userBasicInfoDTO) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public UserBasicInfo findUserById(Long id) {
        return null;
    }

    @Override
    public UserBasicInfo findUserByPhone(String phone) {
        return null;
    }

    @Override
    public List<UserBasicInfo> findUserList(String userName, String phone, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public boolean count(Long id) {
        return false;
    }

    @Override
    public boolean count(String phone) {
        return false;
    }
}
