package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 07.05.2021
 */
@Data
public class HospitalNewsVO {

    /**
     * 资讯标题
     */
    @ApiModelProperty(value = "资讯标题")
    private String newsTitle;

    /**
     * 资讯链接
     */
    @ApiModelProperty(value = "资讯链接")
    private String newsUrl;
}
