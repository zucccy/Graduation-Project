package cn.edu.zucc.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class CryptUtils {

    private static final String salt = "chenyungp";
    /**
     * 用户密码加密
     *
     * @param password 用户密码
     * @return 密文
     */
    public static String cryptAccountPassword(String password) {
        System.out.println(password + salt);
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    /**
     * 加密密码比较校验
     *
     * @param rawPassword 原密码（加密后）
     * @param password    输入的密码
     * @return 是否相等
     */
    public static boolean matchAccountPassword(String rawPassword, String password) {
        return StringUtils.equals(rawPassword, cryptAccountPassword(password));
    }
}
