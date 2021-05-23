package cn.edu.zucc.exception;

import cn.edu.zucc.enums.ResultCodeEnum;

/**
 * Description: 密码错误异常
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class WrongPasswordException extends BaseException {

    public WrongPasswordException(String msg) {
        super(msg, ResultCodeEnum.REQUEST_INFO_ERROR.getCode());
    }

    public WrongPasswordException() {
        super("密码错误", ResultCodeEnum.REQUEST_INFO_ERROR.getCode());
    }
}
