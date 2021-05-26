package cn.edu.zucc.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
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
     * 医院地址经度
     */
    @Column(name = "`longitudes`")
    private BigDecimal longitudes;

    /**
     * 医院地址纬度
     */
    @Column(name = "`latitudes`")
    private BigDecimal latitudes;

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
     * 医院图片
     */
    @Column(name = "`src`")
    private String src;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "`update_time`")
    private Date updateTime;
}