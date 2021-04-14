package cn.edu.zucc.utils;

import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.enums.ResultCodeEnum;
import cn.edu.zucc.exception.BaseException;
import com.github.pagehelper.Page;


/**
 * Description: 通用结果返回对象
 *
 * @author chenyun
 * @since 09.03.2021
 */
public final class ResponseBuilder {

    private ResponseBuilder() {

    }

    public static <T> ResponseVO<T> buildResponse(Long code, String msg, T data) {
        return ResponseVO.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> ResponsePageVO<T> buildPageableResponse(Long code, String msg, Page<T> pageData) {
        return ResponsePageVO.<T>builder()
                .code(code)
                .msg(msg)
                .data(pageData.getResult())
                .total(pageData.getTotal())
                .pageNum(pageData.getPageNum())
                .pageSize(pageData.getPageSize())
                .totalPages(pageData.getPages())
                .build();
    }

    /**
     * 成功返回结果
     *
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> success() {
        return buildResponse(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> success(T data) {
        return buildResponse(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 分页成功返回结果
     *
     * @param data
     * @return ResponsePageVO<T>
     */
    public static <T> ResponsePageVO<T> successPageable(Page<T> data) {
        return buildPageableResponse(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 异常返回结果
     *
     * @param e
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> buildErrorResponse(BaseException e) {
        return buildResponse(e.getCode(), e.getMessage(), null);
    }

}
