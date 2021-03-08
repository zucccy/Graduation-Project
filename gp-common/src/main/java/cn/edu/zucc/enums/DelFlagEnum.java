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
    String desc;

    DelFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
