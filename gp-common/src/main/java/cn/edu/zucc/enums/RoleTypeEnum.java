package cn.edu.zucc.enums;

import lombok.Getter;

/**
 * Description: 角色类型枚举类
 *
 * @author chenyun
 * @since 05.05.2021
 */
@Getter
public enum RoleTypeEnum {

    /**
     * 用户
     */
    USER(1),
    /**
     * 医生
     */
    DOCTOR(2),
    /**
     * 管理员
     */
    ADMIN(3);
    /**
     * 角色类型，1用户 2医生 3管理员，默认为1
     */
    private Integer types;

    RoleTypeEnum(Integer types) {
        this.types = types;
    }
}
