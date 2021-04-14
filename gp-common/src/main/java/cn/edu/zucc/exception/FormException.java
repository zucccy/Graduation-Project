package cn.edu.zucc.exception;

import cn.edu.zucc.enums.ResultCodeEnum;

/**
 * Description: 提交信息错误
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class FormException extends BaseException {

    public FormException(String msg) {
        super(msg, ResultCodeEnum.REQUEST_INFO_ERROR.getCode());
    }
}
