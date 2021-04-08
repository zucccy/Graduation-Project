package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`account`")
public class Account {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "`user_id`")
    private Long userId;

    /**
     * 登录账号/手机号/邮箱
     */
    @Column(name = "`open_code`")
    private String openCode;

    /**
     * 账号类别，1为手机号，2为邮箱，默认为1
     */
    @Column(name = "`category`")
    private Byte category;

    /**
     * 账号状态，1有效，2无效，默认为1
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 最后登录时间
     */
    @Column(name = "`login_time`")
    private Date loginTime;

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