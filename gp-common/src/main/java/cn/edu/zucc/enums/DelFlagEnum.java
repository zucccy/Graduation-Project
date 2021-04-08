package cn.edu.zucc.enums;

/**
 * Description: 删除状态枚举类
 *
 * @author chenyun
 * @since 09.03.2021
 */
public enum DelFlagEnum {

    NO(0, "未删除"),
    YES(1, "已删除");

    int code;
    String message;

    DelFlagEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
