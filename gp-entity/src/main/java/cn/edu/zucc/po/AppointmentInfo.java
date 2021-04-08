package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`appointment_info`")
public class AppointmentInfo {
    /**
     * 预约编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 医生编号
     */
    @Column(name = "`doctor_id`")
    private Long doctorId;

    /**
     * 用户编号
     */
    @Column(name = "`user_id`")
    private Long userId;

    /**
     * 患者编号
     */
    @Column(name = "`patient_id`")
    private Long patientId;

    /**
     * 出诊编号
     */
    @Column(name = "`visit_id`")
    private Long visitId;

    /**
     * 预约状态 默认为1 1已预约，2已退号，3未按时就诊，过号，4预约中， 5预约结束
     */
    @Column(name = "`visit_status`")
    private Byte visitStatus;

    /**
     * 伪删除 1已删除，0未删除，默认为0
     */
    @Column(name = "`is_delete`")
    private Byte isDelete;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;
}