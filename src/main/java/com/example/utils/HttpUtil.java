package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/1/18.
 */
public class HttpUtil {
    public static final String CHAR_SET = "utf-8";
    public static final int CONNECT_TIME_OUT = 15000;
    public static final int READ_TIME_OUT = 15000;

    public static <K, V> String doPost(String url, Map<K, V> param) throws Exception {
        HttpURLConnection conn = null;
        OutputStream out = null;
        BufferedReader in;
        String result = "";
        try {
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept-Charset", CHAR_SET);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIME_OUT);
            out = conn.getOutputStream();
            out.write(MapToString(param).getBytes(CHAR_SET));
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    public static <K, V> String doGet(String url, Map<K, V> param) throws Exception {
        return doGet(url, MapToString(param));
    }

    public static String doGet(String url, String param) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Accept-Charset", CHAR_SET);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIME_OUT);
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

    public static <K, V> String MapToString(Map<K, V> map) throws IOException {
        StringBuffer sb = new StringBuffer();
        // 遍历Map集合
        for (Map.Entry<K, V> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            sb.append(key).append("=").append(URLEncoder.encode(value, CHAR_SET)).append("&");
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }
}
