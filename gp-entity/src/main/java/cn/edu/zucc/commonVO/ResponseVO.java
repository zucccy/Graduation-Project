package cn.edu.zucc.commonVO;

import lombok.Builder;
import lombok.Data;

/**
 * Description: 接口响应类
 *
 * @author chenyun
 * @since 09.03.2021
 */
@Data
@Builder
public class ResponseVO<T> {

    // 响应码
    private String code;

    // 响应信息
    private String msg;

    //响应数据
    private T data;
}
