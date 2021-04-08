package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`doctor_info`")
public class DoctorInfo {
    /**
     * 医生编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 医生名字
     */
    @Column(name = "`doctor_name`")
    private String doctorName;

    /**
     * 医生性别 默认为1 男1女0
     */
    @Column(name = "`sex`")
    private Byte sex;

    /**
     * 医生职位
     */
    @Column(name = "`position`")
    private String position;

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
     * 医生描述
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