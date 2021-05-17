package cn.edu.zucc.utils;

import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.enums.ResultCodeEnum;
import cn.edu.zucc.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: 异常处理
 *
 * @author chenyun
 * @since 14.04.2021
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandling {
    /**
     * 全局未知异常处理
     *
     * @param e 抛出的异常
     * @return -
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO<Void> sendErrorResponse(Throwable e) {
        log.error("unknowing exception", e);
        return ResponseBuilder.buildResponse(ResultCodeEnum.FAILED.getCode(), e.getMessage(), null);
    }

    /**
     * 自定义异常处理
     *
     * @param e 抛出的异常
     * @return -
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO<Void> sendCustomErrResponse(BaseException e) {
        return ResponseBuilder.buildErrorResponse(e);
    }
}
