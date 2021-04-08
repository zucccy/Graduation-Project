package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`visit_rel_time`")
public class VisitRelTime {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 出诊编号
     */
    @Column(name = "`visit_id`")
    private Long visitId;

    /**
     * 出诊时段 1上午 2下午 3晚上，默认为1
     */
    @Column(name = "`visit_period`")
    private Byte visitPeriod;

    /**
     * 出诊日期
     */
    @Column(name = "`visit_day`")
    private Date visitDay;

    /**
     * 出诊具体时间
     */
    @Column(name = "`time_period`")
    private String timePeriod;
}