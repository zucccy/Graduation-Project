package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 23.05.2021
 */
@Data
public class EssayVO {
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String essayTitle;

    /**
     * 文章链接
     */
    @ApiModelProperty(value = "文章链接")
    private String essayUrl;

    /**
     * 文章作者
     */
    @ApiModelProperty(value = "文章作者")
    private String essayAuthor;
}
