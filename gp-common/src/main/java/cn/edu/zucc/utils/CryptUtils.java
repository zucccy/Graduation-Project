package cn.edu.zucc.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class CryptUtils {

    @Value("${spring.datasource.salt}")
    private static String salt;

    /**
     * 用户密码加密
     *
     * @param password 用户密码
     * @return 密文
     */
    public static String cryptAccountPassword(String password) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    /**
     * 加密密码比较校验
     *
     * @param rawPassword 原密码（加密后）
     * @param password    输入的密码
     * @return 是否相等
     */
    public static boolean matchAccountPasswd(String rawPassword, String password) {
        return StringUtils.equals(rawPassword, cryptAccountPassword(password));
    }
}
