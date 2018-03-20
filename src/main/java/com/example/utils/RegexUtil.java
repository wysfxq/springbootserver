package com.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class RegexUtil {
    private static String REGEX_CAR_NUMBER = "^([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领][a-zA-Z](([DF](?![a-zA-Z0-9]*[IO])[0-9]{5})|([0-9]{5}[DF])))|([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领A-Z]{1}[a-zA-Z0-9]{5}[a-zA-Z0-9挂学警港澳]{1})$";
    private static String REGEX_MAC_ADDRESS = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
    private static String REGEX_EMAIL = "^[A-Za-z0-9\\u4e00-\\u9fa5\\._-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+){1,3}$";
    private static String REGEX_ID_CARD = "[1-9]\\d{16}[a-zA-Z0-9]{1}";
    private static String REGEX_MOBILE = "(\\+\\d+)?1[34578]\\d{9}$";
    private static String REGEX_PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
    private static String REGEX_NUMBER = "\\-?[1-9]\\d+";
    public static final String REGEX_IP = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
    public static final String REGEX_POSTCODE = "[1-9]\\d{5}";
    public static final String REGEX_DECIMAL = "\\-?[1-9]\\d+(\\.\\d+)?";
    public static final String REGEX_BLANK = "\\s+";
    public static final String REGEX_CHINESE = "^[一-龥]+$";
    public static final String REGEX_DAY = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
    public static final String REGEX_URL = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
    public static final String REGEX_DOMAIN = "(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)";

    public RegexUtil() {
    }

    public static boolean checkEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public static void main(String[] a) {
        String mail1 = "eee@edu-ing.ml";
        String mail2 = "huahui.wu@qq.com";
        System.out.println(isCarNumber("粤BF12345"));
        System.out.println(isCarNumber("浙A6307Q"));
    }

    public static boolean checkIdCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    public static boolean checkMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean checkPhone(String phone) {
        return Pattern.matches(REGEX_PHONE, phone);
    }

    public static boolean checkDigit(String digit) {
        return Pattern.matches(REGEX_NUMBER, digit);
    }

    public static boolean checkDecimals(String decimals) {
        return Pattern.matches("\\-?[1-9]\\d+(\\.\\d+)?", decimals);
    }

    public static boolean checkBlankSpace(String blankSpace) {
        return Pattern.matches("\\s+", blankSpace);
    }

    public static boolean checkChinese(String chinese) {
        return Pattern.matches("^[一-龥]+$", chinese);
    }

    public static boolean checkBirthday(String birthday) {
        return Pattern.matches("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}", birthday);
    }

    public static boolean checkURL(String url) {
        return Pattern.matches("(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?", url);
    }

    public static String getDomain(String url) {
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", 2);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

    public static boolean checkPostcode(String postcode) {
        return Pattern.matches("[1-9]\\d{5}", postcode);
    }

    public static boolean checkIpAddress(String ipAddress) {
        return Pattern.matches("[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))", ipAddress);
    }

    public static boolean checkMacAddress(String macAddress) {
        return Pattern.matches(REGEX_MAC_ADDRESS, macAddress);
    }

    public static boolean isCarNumber(String data) {
        return Pattern.matches(REGEX_CAR_NUMBER, data);
    }
}
