package com.example.utils;

import java.util.Random;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class RandomCodeUtil {
    private static String[] FEED_NUMBERS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static String[] FEED_CHARS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public RandomCodeUtil() {
    }

    public static String createRandomCode(int length, boolean isNumeric) {
        return isNumeric ? createRandomNumbers(length) : createRandomStrings(length);
    }

    public static String createRandomNumbers(int length) {
        StringBuilder strBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            strBuilder.append(FEED_NUMBERS[random.nextInt(FEED_NUMBERS.length)]);
        }

        return strBuilder.toString();
    }

    public static String createRandomStrings(int length) {
        StringBuilder strBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            strBuilder.append(FEED_CHARS[random.nextInt(FEED_CHARS.length)]);
        }

        return strBuilder.toString();
    }

    public static String createRandomCode(int length) {
        return createRandomCode(length, true);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println("【" + i + "】" + "      【the verification code is】       " + createRandomCode(6));
        }

    }
}
