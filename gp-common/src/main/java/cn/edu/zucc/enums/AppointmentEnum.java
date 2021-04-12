package cn.edu.zucc.enums;

import lombok.Getter;

/**
 * Description: 预约状态枚举类
 *
 * @author chenyun
 * @since 12.04.2021
 */
@Getter
public enum AppointmentEnum {

    /**
     * 已预约
     */
    WAITING(1),
    /**
     * 已退号
     */
    CANCEL(2),
    /**
     * 未按时就诊，过号
     */
    MISSING(3),
    /**
     * 预约中
     */
    WORKING(4),
    /**
     * 预约结束
     */
    FINISH(5);
    /**
     * 预约状态 默认为1 1已预约，2已退号，3未按时就诊，过号，4预约中， 5预约结束
     */
    private Integer status;

    AppointmentEnum(Integer status) {
        this.status = status;
    }
}
