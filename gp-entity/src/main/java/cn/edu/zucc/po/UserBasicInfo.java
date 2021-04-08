package cn.edu.zucc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`user_basic_info`")
public class UserBasicInfo {
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
     * 登录密码，md5加密
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 手机号
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 用户头像
     */
    @Column(name = "`profile`")
    private String profile;

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