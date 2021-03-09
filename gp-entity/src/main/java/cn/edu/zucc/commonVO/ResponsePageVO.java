package cn.edu.zucc.commonVO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description: 分页结果接口响应类
 *
 * @author chenyun
 * @since 09.03.2021
 */
@Data
@Builder
public class ResponsePageVO<T> {

    // 响应码
    private String code;

    // 响应信息
    private String msg;

    // 响应数据
    private List<T> data;

    // 总页数
    private Integer totalPages;

    //总条数
    private Long total;

    // 页号
    private Integer pageNum;

    private Integer pageSize;
}
