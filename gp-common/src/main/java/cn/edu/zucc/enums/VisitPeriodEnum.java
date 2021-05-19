package cn.edu.zucc.enums;

import lombok.Getter;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 19.05.2021
 */
@Getter
public enum VisitPeriodEnum {

    /**
     * 上午
     */
    MORNING(1),
    /**
     * 下午
     */
    AFTERNOON(2),
    /**
     * 晚上
     */
    NIGHT(3);
    /**
     * 出诊时段 1上午 2下午 3晚上，默认为1
     */
    private Integer types;

    VisitPeriodEnum(Integer types) {
        this.types = types;
    }
}
