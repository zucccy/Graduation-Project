package cn.edu.zucc.exception;

/**
 * Description: 基本异常，所有自定义异常都要继承它
 *
 * @author chenyun
 * @since 09.03.2021
 */
public class BaseException extends RuntimeException {

    private final long code;

    BaseException(String msg, long code) {
        super(msg);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
