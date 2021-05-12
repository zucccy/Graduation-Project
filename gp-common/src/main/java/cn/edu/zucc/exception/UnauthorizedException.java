package cn.edu.zucc.exception;

import org.springframework.http.HttpStatus;

/**
 * @since 05-10-2021
 **/
public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("未登录", HttpStatus.UNAUTHORIZED.value());
    }
}
