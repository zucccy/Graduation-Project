package cn.edu.zucc;

import cn.edu.zucc.dto.UserBasicInfoDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.po.UserBasicInfo;

import java.util.List;

/**
 * Description: 普通用户基本信息接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface UserBasicInfoService {

    /**
     * 发送注册短信
     *
     * @param phone 手机号
     * @return 是否成功
     */
    boolean sendMessage(String phone);

    /**
     * 校验短信验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 是否正确
     */
    boolean verifyCode(String phone, String code);

    /**
     * 添加普通用户
     *
     * @param userRegisterDTO 用户注册参数
     * @return 是否成功
     */
    boolean insert(UserRegisterDTO userRegisterDTO);

    /**
     * 更新普通用户
     *
     * @param id               用户编号
     * @param userBasicInfoDTO 用户基本信息参数
     * @return 是否成功
     */
    boolean update(Long id, UserBasicInfoDTO userBasicInfoDTO);

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
     * @return cn.edu.zucc.po.UserBasicInfo
     */
    UserBasicInfo findUserById(Long id);

    /**
     * 根据手机号获取用户信息
     *
     * @param phone 手机号
     * @return cn.edu.zucc.po.UserBasicInfo
     */
    UserBasicInfo findUserByPhone(String phone);

    /**
     * 根据用户名、手机号查找用户列表
     *
     * @param userName 用户名
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @return java.util.List<cn.edu.zucc.po.UserBasicInfo>
     */
    List<UserBasicInfo> findUserList(String userName, Integer pageNum, Integer pageSize);

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
