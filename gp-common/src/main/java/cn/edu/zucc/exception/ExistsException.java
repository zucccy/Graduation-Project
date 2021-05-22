package cn.edu.zucc.exception;

import cn.edu.zucc.enums.ResultCodeEnum;

/**
 * Description: 已存在异常
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class ExistsException extends BaseException {

    public ExistsException(String msg) {
        super(msg, ResultCodeEnum.REQUEST_INFO_ERROR.getCode());
    }

    public ExistsException() {
        super("已存在", ResultCodeEnum.REQUEST_INFO_ERROR.getCode());
    }
}
