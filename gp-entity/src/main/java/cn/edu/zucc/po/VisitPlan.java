package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`visit_plan`")
public class VisitPlan {
    /**
     * 出诊编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 所属医院
     */
    @Column(name = "`hospital_id`")
    private Long hospitalId;

    /**
     * 所属科室
     */
    @Column(name = "`office_id`")
    private Long officeId;

    /**
     * 医生编号
     */
    @Column(name = "`doctor_id`")
    private Long doctorId;

    /**
     * 出诊类型 0普通门诊1专家门诊，默认0
     */
    @Column(name = "`visit_type`")
    private Byte visitType;

    /**
     * 伪删除 1已删除，0未删除
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