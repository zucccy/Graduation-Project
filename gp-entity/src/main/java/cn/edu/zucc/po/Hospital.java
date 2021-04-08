package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`hospital`")
public class Hospital {
    /**
     * 医院编号，1001开始
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 医院名称
     */
    @Column(name = "`hospital_name`")
    private String hospitalName;

    /**
     * 医院地址
     */
    @Column(name = "`address`")
    private String address;

    /**
     * 医院电话，可以有多个
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 医院描述
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