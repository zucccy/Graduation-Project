package cn.edu.zucc.enums;

/**
 * Description: 通用状态枚举类
 *
 * @author chenyun
 * @since 09.03.2021
 */
public enum CommonStatusEnum {

    YES(1, "启用"),
    NO(0, "禁用");

    int code;
    String desc;

    CommonStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
