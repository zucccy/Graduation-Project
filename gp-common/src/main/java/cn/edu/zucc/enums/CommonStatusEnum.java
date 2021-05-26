package cn.edu.zucc.enums;

/**
 * Description: 通用状态枚举类
 *
 * @author chenyun
 * @since 09.03.2021
 */
public enum CommonStatusEnum {

    YES(1, "启用"),
    NO(2, "禁用");

    int code;
    String message;

    CommonStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
