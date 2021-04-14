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
@Data
@ApiModel(value = "UpdatePasswordDTO", description = "用户修改密码参数")
public class UpdatePasswordDTO {

    /**
     * 当前密码
     */
    @ApiModelProperty(value = "当前密码")
    private String rowPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;

}
