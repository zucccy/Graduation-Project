package cn.edu.zucc.exception;

import cn.edu.zucc.enums.ResultCodeEnum;

/**
 * Description: 资源未找到
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class SourceNotFoundException extends BaseException {

    public SourceNotFoundException(String msg) {
        super(msg, ResultCodeEnum.NOT_FOUND.getCode());
    }
}
