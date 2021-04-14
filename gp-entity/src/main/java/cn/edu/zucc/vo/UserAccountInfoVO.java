package cn.edu.zucc.vo;

import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
@Data
public class UserAccountInfoVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String profile;
}
