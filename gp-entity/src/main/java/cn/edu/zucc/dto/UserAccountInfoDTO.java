package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Description: TODO
 *
 * @author chenyun
 * @since 09.04.2021
 */
@ApiModel(value = "UserAccountInfoDTO", description = "用户基本信息参数")
@Data
public class UserAccountInfoDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 登录密码，md5加密
     */
    @ApiModelProperty(value = "登录密码，md5加密")
    private String password;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String profile;
}