package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`hospital_rel_office`")
public class HospitalRelOffice {
    /**
     * 医院科室关系编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 医院编号
     */
    @Column(name = "`hospital_id`")
    private Long hospitalId;

    /**
     * 科室编号
     */
    @Column(name = "`office_id`")
    private Long officeId;

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