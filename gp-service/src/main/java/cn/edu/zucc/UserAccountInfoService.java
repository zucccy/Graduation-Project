package cn.edu.zucc;

import cn.edu.zucc.dto.UserAccountInfoUpdateDTO;
import cn.edu.zucc.dto.UserLoginDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.po.UserAccountInfo;

import java.util.List;

/**
 * Description: 普通用户基本信息接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface UserAccountInfoService {

//    /**
//     * 发送注册短信
//     *
//     * @param phone 手机号
//     * @return 是否成功
//     */
//    boolean sendMessage(String phone);
//
//    /**
//     * 校验短信验证码
//     *
//     * @param phone 手机号
//     * @param code  验证码
//     * @return 是否正确
//     */
//    boolean verifyCode(String phone, String code);

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return 是否成功
     */
    UserAccountInfo login(UserLoginDTO userLoginDTO);

    /**
     * 注册普通用户
     *
     * @param userRegisterDTO 用户注册参数
     * @return 是否成功
     */
    boolean register(UserRegisterDTO userRegisterDTO);

    /**
     * 修改个人信息
     *
     * @param id                       用户编号
     * @param userAccountInfoUpdateDTO 个人信息修改参数
     * @return 是否成功
     */
    boolean update(Long id, UserAccountInfoUpdateDTO userAccountInfoUpdateDTO);

    /**
     * 删除普通用户
     *
     * @param id 用户编号
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 根据用户编号获取用户信息
     *
     * @param id 用户编号
     * @return cn.edu.zucc.po.UserAccountInfo
     */
    UserAccountInfo findUserById(Long id);

    /**
     * 根据前端帐号获取用户信息
     *
     * @param openCode 前端帐号
     * @return cn.edu.zucc.po.UserAccountInfo
     */
    UserAccountInfo findUserByOpenCode(String openCode);

    /**
     * 根据用户名模糊查找用户列表
     *
     * @param userName 用户名
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.UserAccountInfo>
     */
    List<UserAccountInfo> findUserList(String userName, Integer pageNum, Integer pageSize);

    /**
     * 判断用户是否存在
     *
     * @param id 用户编号
     * @return 是否存在
     */
    boolean count(Long id);

    /**
     * 判断用户是否存在
     *
     * @param phone 手机号
     * @return 是否存在
     */
    boolean count(String phone);
}
