package cn.edu.zucc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 26.05.2021
 */
@ApiModel(value = "AdminLoginDTO", description = "管理员登录参数")
@Data
public class AdminLoginDTO {

    /**
     * 账号
     */
    @ApiModelProperty(value = "帐号")
    private String userName;

    /**
     * 登录密码，md5加密
     */
    @ApiModelProperty(value = "登录密码，md5加密")
    private String password;

}
