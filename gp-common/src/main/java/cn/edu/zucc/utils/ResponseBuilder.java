package cn.edu.zucc.utils;

import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.constant.ResponseMsg;
import cn.edu.zucc.exception.BaseException;
import com.github.pagehelper.Page;
import org.springframework.http.HttpStatus;


/**
 * Description: TODO
 *
 * @author chenyun
 * @since 09.03.2021
 */
public final class ResponseBuilder {

    private ResponseBuilder() {

    }

    public static <T> ResponseVO<T> buildResponse(String code, String msg, T data) {
        return ResponseVO.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> ResponsePageVO<T> buildPageableResponse(String code, String msg, Page<T> pageData) {
        return ResponsePageVO.<T>builder()
                .code(code)
                .msg(msg)
                .data(pageData.getResult())
                .total(pageData.getTotal())
                .pageNum(pageData.getPageNum() + 1)
                .pageSize(pageData.getPageSize())
                .build();
    }

    public static <T> ResponseVO<T> buildSuccessResponse() {
        return buildResponse(String.valueOf(HttpStatus.OK.value()), ResponseMsg.SUCCESS, null);
    }

    public static <T> ResponseVO<T> buildSuccessResponse(T data) {
        return buildResponse(String.valueOf(HttpStatus.OK.value()), ResponseMsg.SUCCESS, data);
    }

    public static <T> ResponsePageVO<T> buildSuccessPageableReponsebuild(Page<T> data) {
        return buildPageableResponse(String.valueOf(HttpStatus.OK.value()), ResponseMsg.SUCCESS, data);
    }


    public static <T> ResponseVO<T> buildErrorResponse(BaseException e) {
        return buildResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
    }
}
