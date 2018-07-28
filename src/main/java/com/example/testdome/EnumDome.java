package com.example.testdome;

/**
 * Created by yinsheng.wang on 2018/7/25.
 */
public class EnumDome {
    public enum ColorStatus {
        RED("红色"), GREEN("绿色"), BLANK("白色"), YELLO("黄色");
        private String colorValue;


        ColorStatus(String value) {
            this.colorValue = value;
        }

        public String getColorValue() {
            return colorValue;
        }

        public void setColorValue(String colorValue) {
            this.colorValue = colorValue;
        }
    }

    public enum Color {
        RED(1001, "红色"), GREEN(1002, "绿色"), BLANK(1003, "白色"), YELLO(1004, "黄色");
        private int code;
        private String name;

        Color(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 普通方法
        public static String getName(int code) {
            for (Color c : Color.values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return null;
        }
    }
}
