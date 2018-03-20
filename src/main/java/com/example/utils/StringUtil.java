package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class StringUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public StringUtil() {
    }

    public static boolean isEmpty(String value) {
        int strLen;
        if (value != null && (strLen = value.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String toLowerCaseFirstOne(String s) {
        return isEmpty(s) ? s : (Character.isLowerCase(s.charAt(0)) ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1));
    }

    public static String toUpperCaseFirstOne(String s) {
        return isEmpty(s) ? s : (Character.isUpperCase(s.charAt(0)) ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1));
    }

    public static String lineToHump(String str) {
        if (null != str && !"".equals(str)) {
            str = str.toLowerCase();
            Matcher matcher = linePattern.matcher(str);
            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }

            matcher.appendTail(sb);
            str = sb.toString();
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
            return str;
        } else {
            return str;
        }
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        } else {
            char[] chars = obj.toString().toCharArray();
            int length = chars.length;
            if (length < 1) {
                return false;
            } else {
                int i = 0;
                if (length > 1 && chars[0] == 45) {
                    i = 1;
                }

                while (i < length) {
                    if (!Character.isDigit(chars[i])) {
                        return false;
                    }

                    ++i;
                }

                return true;
            }
        }
    }

    public static boolean areEmpty(String... values) {
        boolean result = true;
        if (values != null && values.length != 0) {
            String[] var2 = values;
            int var3 = values.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                result &= isEmpty(value);
            }
        } else {
            result = true;
        }

        return result;
    }

    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values != null && values.length != 0) {
            String[] var2 = values;
            int var3 = values.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                result &= isNotEmpty(value);
            }
        } else {
            result = false;
        }

        return result;
    }

    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); ++i) {
                out.append(unicode.charAt(i));
            }
        }

        return out.toString();
    }

    public static String toUnderlineStyle(String name) {
        StringBuilder newName = new StringBuilder();

        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    newName.append("_");
                }

                newName.append(Character.toLowerCase(c));
            } else {
                newName.append(c);
            }
        }

        return newName.toString();
    }

    public static <T> String toString(T t) {
        return t == null ? null : (!(t instanceof byte[]) && !(t instanceof Byte[]) ? t.toString() : new String((byte[]) ((byte[]) t)));
    }

    public static String toString(byte[] data, int offset, int length, String charset) {
        if (data == null) {
            return null;
        } else {
            try {
                return new String(data, offset, length, charset);
            } catch (UnsupportedEncodingException var5) {
                throw new RuntimeException(var5.getMessage(), var5);
            }
        }
    }

    public static byte[] toBytes(String data) {
        return toBytes(data, "utf-8");
    }

    public static byte[] toBytes(String data, String charset) {
        if (data == null) {
            return null;
        } else {
            try {
                return data.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException(var3.getMessage(), var3);
            }
        }
    }

    public static boolean isDouble(String arg0) {
        if (arg0 != null && arg0.length() != 0) {
            try {
                Double.parseDouble(arg0);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String nullToEmpty(String arg) {
        return arg != null && !"null".equals(arg.toLowerCase()) ? arg : "";
    }

    public static String joinString(String[] array, String separator) {
        return joinString(Arrays.asList(array), separator);
    }

    public static String joinString(List<String> list, String separator) {
        return joinString(list.iterator(), separator);
    }

    public static String joinLong(List<Long> list, String separator) {
        return joinString(list.iterator(), separator);
    }

    public static String joinInteger(List<Integer> list, String separator) {
        return joinString(list.iterator(), separator);
    }

    public static String joinString(Collection<String> coll, String separator) {
        return joinString(coll.iterator(), separator);
    }

    public static <T> String joinString(Iterator<T> list, String separator) {
        StringBuilder strBuilder;
        for (strBuilder = new StringBuilder(); list.hasNext(); strBuilder.append(list.next())) {
            if (strBuilder.length() > 0) {
                strBuilder.append(separator);
            }
        }

        return strBuilder.toString();
    }

    public static String[] split(String str, String separator) {
        List list = splitToList(str, separator);
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static List<String> splitToList(String str, String separator) {
        ArrayList list = new ArrayList();
        if (isEmpty(str)) {
            return list;
        } else if (isEmpty(separator)) {
            list.add(str);
            return list;
        } else {
            int lastIndex = -1;
            int index = str.indexOf(separator);
            if (-1 == index) {
                list.add(str);
                return list;
            } else {
                while (index >= 0) {
                    if (index > lastIndex) {
                        list.add(str.substring(lastIndex + 1, index));
                    } else {
                        list.add("");
                    }

                    lastIndex = index;
                    index = str.indexOf(separator, index + 1);
                    if (index == -1) {
                        list.add(str.substring(lastIndex + 1, str.length()));
                    }
                }

                return list;
            }
        }
    }

    public static String lpad(String str, int length, String pad) {
        return appendPad(str, length, pad, false);
    }

    public static String rpad(String str, int length, String pad) {
        return appendPad(str, length, pad, true);
    }

    private static String appendPad(String str, int length, String pad, boolean right) {
        StringBuilder builder = new StringBuilder();
        if (right) {
            builder.append(str);
        }

        int num = right ? length : length - str.length();
        if (isNotEmpty(pad)) {
            while (builder.length() < num) {
                for (int i = 0; i < pad.length(); ++i) {
                    builder.append(pad.charAt(i));
                    if (builder.length() >= num) {
                        break;
                    }
                }
            }
        }

        if (!right) {
            builder.append(str);
        }

        return builder.toString();
    }

    public static String firstCharacterToUpper(String srcStr) {
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }

    public static String cutLast(String srcStr) {
        return srcStr.substring(0, srcStr.length() - 1);
    }

    public static String replaceUnderlineAndfirstToUpper(String srcStr, String org, String ob) {
        String newString = "";
        boolean first = false;

        while (srcStr.indexOf(org) != -1) {
            int first1 = srcStr.indexOf(org);
            if (first1 != srcStr.length()) {
                newString = newString + srcStr.substring(0, first1) + ob;
                srcStr = srcStr.substring(first1 + org.length(), srcStr.length());
                srcStr = firstCharacterToUpper(srcStr);
            }
        }

        newString = newString + srcStr;
        return newString;
    }

    public static String stringCut(String aString, int aLen, String aHintStr) {
        if (aString == null) {
            return aString;
        } else {
            int lLen = aString.length();

            int i;
            for (i = 0; aLen >= 0 && i < lLen; ++i) {
                if (isBigChar(aString.charAt(i))) {
                    aLen -= 2;
                } else {
                    --aLen;
                }
            }

            if (aLen >= 0) {
                return aString;
            } else if (aHintStr == null) {
                return aString.substring(0, i - 1);
            } else {
                aLen -= aHintStr.length();

                while (aLen < 0) {
                    --i;
                    if (i < 0) {
                        break;
                    }

                    if (isBigChar(aString.charAt(i))) {
                        aLen += 2;
                    } else {
                        ++aLen;
                    }
                }

                return aHintStr == null ? aString.substring(0, i) : aString.substring(0, i) + aHintStr;
            }
        }
    }

    public static boolean startsWith(String str, String prefix) {
        if (str != null && prefix != null) {
            if (str.startsWith(prefix)) {
                return true;
            } else if (str.length() < prefix.length()) {
                return false;
            } else {
                String lcStr = str.substring(0, prefix.length());
                return lcStr.equals(prefix);
            }
        } else {
            return false;
        }
    }

    public static boolean isBigChar(char c) {
        return c < 0 || c > 256;
    }

    public static String trim(String arg) {
        return arg == null ? null : arg.trim();
    }

    public static double comDouble(Double v1, Double v2, char type) {
        return comDouble(v1, v2, type, 4);
    }

    public static double comDouble(Double v1, Double v2, char type, int scale) {
        BigDecimal ret = new BigDecimal("0.0");
        if (v1 != null) {
            ret = ret.add(new BigDecimal(v1.toString()));
        }

        if (v2 != null) {
            switch (type) {
                case '*':
                    ret = ret.multiply(new BigDecimal(v2.toString()));
                    break;
                case '+':
                    ret = ret.add(new BigDecimal(v2.toString()));
                case ',':
                case '.':
                default:
                    break;
                case '-':
                    ret = ret.subtract(new BigDecimal(v2.toString()));
                    break;
                case '/':
                    ret = ret.divide(new BigDecimal(v2.toString()), 10, 6);
            }
        }

        return ret.setScale(scale, 6).doubleValue();
    }

    public static void copyProperties(Object source, Object target) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Class sourceClass = source.getClass();
        Class targetClass = target.getClass();
        Field[] var5 = sourceFields;
        int var6 = sourceFields.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            Field f = var5[var7];

            try {
                String e = f.getName();
                Field field = sourceClass.getDeclaredField(e);
                Class type = field.getType();
                String head = "";
                if (type.getName().contains("boolean")) {
                    head = "is";
                } else {
                    head = "get";
                }

                String sourceMethodName = head + e.substring(0, 1).toUpperCase() + e.substring(1);
                Method sourceMethod = sourceClass.getMethod(sourceMethodName, (Class[]) null);
                Object fieldValue = sourceMethod.invoke(source, (Object[]) null);
                if (fieldValue != null) {
                    String targetMethodName = "set" + e.substring(0, 1).toUpperCase() + e.substring(1);
                    Method targetMethod = targetClass.getMethod(targetMethodName, new Class[]{fieldValue.getClass()});
                    targetMethod.invoke(target, new Object[]{fieldValue});
                }
            } catch (SecurityException var18) {
                var18.printStackTrace();
            } catch (IllegalArgumentException var19) {
                var19.printStackTrace();
                System.out.println("目标对象无 " + f.getName() + "属性");
            } catch (IllegalAccessException var20) {
                System.out.println("不能访问非公开属性");
            } catch (InvocationTargetException var21) {
                var21.printStackTrace();
            } catch (NoSuchMethodException var22) {
                var22.printStackTrace();
            } catch (NoSuchFieldException var23) {
                var23.printStackTrace();
            } catch (Exception var24) {
                var24.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(equals((Object) null, (Object) null));
        System.out.println(equals("12312312312111", (Object) null));
        System.out.println(equals("12313", Integer.valueOf(12313)));
        System.out.println(equals(Long.valueOf(123L), Integer.valueOf(123)));
    }

    public static boolean contains(String src, String... args) {
        if (args.length == 0) {
            return false;
        } else {
            String[] var2 = args;
            int var3 = args.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String arg = var2[var4];
                if (contain(src, arg)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean contain(String src, String arg) {
        return src == null ? arg == null : (arg != null ? src.contains(arg) : false);
    }

    public static String reverseString(String str) {
        if (str != null && str.length() != 0) {
            str = str.trim();
            int strl = str.length();
            if (strl > 1) {
                StringBuffer sb = new StringBuffer(str);
                return sb.reverse().toString();
            } else {
                return str;
            }
        } else {
            return str;
        }
    }

    public static String roundStr(String v, int scale) {
        if (isNumeric(v)) {
            return "";
        } else if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(v);
            b = b.setScale(scale, 4);
            return b.toPlainString();
        }
    }

    public static double setScale(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(v);
            b = b.setScale(scale, 4);
            return b.doubleValue();
        }
    }

    public static String fakeString(String str, boolean limitFakeNum) {
        if (isEmpty(str)) {
            return str;
        } else {
            int fakedNum = 0;
            byte maxFakeNum = 4;
            int strLength = str.length();
            byte showNum = 3;
            boolean isShort = strLength / 2 <= showNum;
            StringBuilder strBuilder = new StringBuilder();

            for (int i = 0; i < strLength; ++i) {
                if (isShort) {
                    if (i < strLength / 2) {
                        strBuilder.append(str.charAt(i));
                    } else {
                        strBuilder.append('*');
                    }
                } else if (!isShort && (i < showNum || strLength - i <= showNum)) {
                    strBuilder.append(str.charAt(i));
                } else if (!limitFakeNum || fakedNum < maxFakeNum) {
                    ++fakedNum;
                    strBuilder.append('*');
                }
            }

            return strBuilder.toString();
        }
    }

    public static <T> String getStringArgs(T... args) {
        StringBuilder str = new StringBuilder();
        Object[] var2 = args;
        int var3 = args.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Object arg = var2[var4];
            str.append(toString(arg));
            str.append(",");
        }

        return str.toString();
    }

    public static String splitByFix(String data, int sectionLen, String separator) {
        if (isEmpty(data)) {
            return data;
        } else {
            int secs = (data.length() + sectionLen - 1) / sectionLen;
            int x = 0;
            StringBuilder strBuilder = new StringBuilder(data);

            for (int i = 1; i < secs; ++i) {
                int idx = i * sectionLen + x;
                strBuilder.insert(idx, separator);
                x += separator.length();
            }

            return strBuilder.toString();
        }
    }

    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static boolean equals(Object data, Object otherData) {
        return data == otherData ? true : (data != null && otherData != null ? data.equals(otherData) : false);
    }

    public static String getString(Map<String, Object> params, String key) {
        Object value = params.get(key);
        return value == null ? null : value.toString();
    }
    public static String getFileName(String pathandname) {
        /**
         * 仅保留文件名不保留后缀
         */
        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }
    }
    /**
     * 保留文件名及后缀
     */
    public static String getFileNameWithSuffix(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        if (start != -1 ) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }
}
