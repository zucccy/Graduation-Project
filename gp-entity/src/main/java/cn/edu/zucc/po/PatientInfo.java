package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`patient_info`")
public class PatientInfo {
    /**
     * 患者编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 患者名称
     */
    @Column(name = "`patient_name`")
    private String patientName;

    /**
     * 患者手机号
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 患者身份证号
     */
    @Column(name = "`id_card`")
    private String idCard;

    /**
     * 患者性别，1男0女，默认为1
     */
    @Column(name = "`sex`")
    private Byte sex;

    /**
     * 患者生日
     */
    @Column(name = "`birthday`")
    private Date birthday;

    /**
     * 病情描述
     */
    @Column(name = "`description`")
    private String description;

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