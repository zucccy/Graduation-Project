package cn.edu.zucc.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`user_account_info`")
public class UserAccountInfo {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "`user_name`")
    private String userName;

    /**
     * 手机号
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "`email`")
    private String email;

    /**
     * 登录密码，md5加密
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 用户头像
     */
    @Column(name = "`profile`")
    private String profile;

    /**
     * 角色类型，1用户 2医生 3管理员，默认为1
     */
    @Column(name = "`role_type`")
    private Byte roleType;

    /**
     * 状态 1有效 2无效，默认为1
     */
    @Column(name = "`status`")
    private Byte status;

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