package cn.edu.zucc.enums;

/**
 * Description: 结果码枚举类
 *
 * @author chenyun
 * @since 07.04.2021
 */
public enum ResultCodeEnum {
    /**
     * 操作成功码
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败码
     */
    FAILED(500, "操作失败"),

    /**
     * 参数错误码
     */
    VALIDATE_FAILED(404, "参数检验失败"),

    /**
     * 会话过时码
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    /**
     * 权限不足码
     */
    FORBIDDEN(403, "没有相关权限");

    /**
     * 返回结果码
     */
    private long code;

    /**
     * 返回信息内容
     */
    private String message;

    /**
     * 私有类构造器
     *
     * @param code    结果码
     * @param message 信息内容
     */

    ResultCodeEnum(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
