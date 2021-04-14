package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
@ApiModel(value = "UserLoginDTO", description = "用户登录参数")
@Data
public class UserLoginDTO {

    /**
     * 前端传来帐号
     */
    @ApiModelProperty(value = "前端传来帐号")
    private String openCode;

    /**
     * 登录密码，md5加密
     */
    @ApiModelProperty(value = "登录密码，md5加密")
    private String password;

}
