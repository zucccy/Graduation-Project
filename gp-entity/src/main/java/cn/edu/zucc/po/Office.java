package cn.edu.zucc.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`office`")
public class Office {
    /**
     * 科室编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级科室编号，从1开始，顶级为0
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 科室名称
     */
    @Column(name = "`office_name`")
    private String officeName;

    /**
     * 科室介绍
     */
    @Column(name = "`description`")
    private String description;

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