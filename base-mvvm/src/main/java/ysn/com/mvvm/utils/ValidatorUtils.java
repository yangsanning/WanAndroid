package ysn.com.mvvm.utils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author yangsanning
 * @ClassName ValidatorUtils
 * @Description 正则表达式验证
 * @Date 2020/5/27
 */
public class ValidatorUtils {

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[3456789]\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：验证base64
     */
    public static final String REGEX_BASE64 = "^data:image/(png|jpg|jpeg|gif|bmp);base64,.*";

    /**
     * 正则表达式: 验证是否纯数字
     */
    public static final String REGEX_NUMBER = "^\\d+$";

    /**
     * 校验用户名
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 验证base64
     */
    public static boolean isBase64(String base64) {
        return Pattern.matches(REGEX_BASE64, base64);
    }

    /**
     * 验证是否纯数字
     */
    public static boolean isNumber(String number) {
        return Pattern.matches(REGEX_NUMBER, number);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(final CharSequence cs) {
        if (isNull(cs) || (cs.toString().trim().length()) == 0) {
            return true;
        }
        for (int i = 0; i < cs.toString().length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmptyList(List list) {
        return !isEmptyList(list);
    }
}