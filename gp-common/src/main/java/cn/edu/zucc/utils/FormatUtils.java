package cn.edu.zucc.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
public class FormatUtils {

    private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9_-]{1,20}@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+){1,10}$");
    private static final Pattern PHONE_NUMBER = Pattern.compile("^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$");
    private static final Pattern ID_CARD_PATTERN = Pattern.compile("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");

    public static boolean isEmail(String s) {
        return !StringUtils.isBlank(s) && s.length() <= 50 && EMAIL.matcher(s).matches();
    }

    public static boolean isPhoneNumber(String s) {
        return !StringUtils.isBlank(s) && PHONE_NUMBER.matcher(s).matches();
    }

    public static boolean isIdCard(String s) {
        return !StringUtils.isBlank(s) && ID_CARD_PATTERN.matcher(s).matches();
    }
}
