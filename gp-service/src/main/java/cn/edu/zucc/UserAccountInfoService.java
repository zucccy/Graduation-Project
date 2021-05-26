package cn.edu.zucc;

import cn.edu.zucc.dto.AdminLoginDTO;
import cn.edu.zucc.dto.UpdatePasswordDTO;
import cn.edu.zucc.dto.UserAccountInfoDTO;
import cn.edu.zucc.dto.UserAccountInfoUpdateDTO;
import cn.edu.zucc.dto.UserLoginDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.po.UserAccountInfo;
import cn.edu.zucc.vo.MyAppointmentListVO;

import java.util.List;

/**
 * Description: 普通用户基本信息接口
 *
 * @author chenyun
 * @since 09.04.2021
 */
public interface UserAccountInfoService {

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return 是否成功
     */
    UserAccountInfo login(UserLoginDTO userLoginDTO);

    /**
     * 管理员登录
     *
     * @param adminLoginDTO
     * @return void
     */
    boolean adminLogin(AdminLoginDTO adminLoginDTO);

    /**
     * 注册普通用户
     *
     * @param userRegisterDTO 用户注册参数
     * @return 是否成功
     */
    boolean register(UserRegisterDTO userRegisterDTO);

    /**
     * 管理员新增用户
     *
     * @param userAccountInfoDTO
     * @return 是否成功
     */
    boolean insert(UserAccountInfoDTO userAccountInfoDTO);

    /**
     * 修改个人信息
     *
     * @param id                       用户编号
     * @param userAccountInfoUpdateDTO 个人信息修改参数
     * @return 是否成功
     */
    boolean update(Long id, UserAccountInfoUpdateDTO userAccountInfoUpdateDTO);

    /**
     * 管理员修改个人信息
     *
     * @param id                 用户编号
     * @param userAccountInfoDTO 个人信息参数
     * @return 是否成功
     */
    boolean update(Long id, UserAccountInfoDTO userAccountInfoDTO);

    /**
     * 管理员禁用用户
     *
     * @param id     用户编号
     * @param status 用户状态
     * @return boolean
     */
    boolean disableAndEnableUser(Long id, Integer status);

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
    boolean countByPhone(String phone);

    /**
     * 判断用户是否存在
     *
     * @param email 邮箱
     * @return 是否存在
     */
    boolean countByEmail(String email);

    /**
     * 用户修改密码
     *
     * @param id                用户编号
     * @param updatePasswordDTO 用户修改密码参数
     * @return 是否成功
     */
    void updatePassword(Long id, UpdatePasswordDTO updatePasswordDTO);

    /**
     * 根据用户编号查找我的预约信息
     *
     * @param id 用户编号
     * @return java.util.List<cn.edu.zucc.vo.MyAppointmentVO>
     */
    List<MyAppointmentListVO> getMyAppointments(Long id);

    /**
     * 获取所有用户列表（可按照用户名查询）
     *
     * @param userName
     * @param pageNum
     * @param pageSize
     * @return java.util.List<cn.edu.zucc.po.UserAccountInfo>
     */
    List<UserAccountInfo> getAllUsers(String userName, Integer pageNum, Integer pageSize);
}
