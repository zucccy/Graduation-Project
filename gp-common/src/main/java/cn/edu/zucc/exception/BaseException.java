package cn.edu.zucc.exception;

/**
 * Description: 基本异常，所有自定义异常都要继承它
 *
 * @author chenyun
 * @since 09.03.2021
 */
public class BaseException extends RuntimeException {

    private final String code;

    BaseException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
