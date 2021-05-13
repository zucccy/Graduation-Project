package cn.edu.zucc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 13.05.2021
 */
@Data
public class OfficeVO {

    /**
     * 科室编号
     */
    @ApiModelProperty(value = "科室编号")
    private Long id;

    /**
     * 父级科室编号，从1开始，顶级为0
     */
    @ApiModelProperty(value = "父级科室编号")
    private Long parentId;

    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    private String officeName;

    /**
     * 科室介绍
     */
    @ApiModelProperty(value = "科室介绍")
    private String description;

    /**
     * 子科室列表
     */
    @ApiModelProperty(value = "子科室列表")
    private List<ChildOfficeVO> childOfficeList;
}
