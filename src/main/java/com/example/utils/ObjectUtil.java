package com.example.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class ObjectUtil {
    public ObjectUtil() {
    }

    public static boolean hasNull(Object... objects) {
        Object[] var1 = objects;
        int var2 = objects.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            if (Objects.isNull(o)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNonNull(Object... objects) {
        return !hasNull(objects);
    }

    public static boolean hasEmpty(Object... values) {
        if (null != values && values.length != 0) {
            boolean isEmpty = false;
            Object[] var2 = values;
            int var3 = values.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object value = var2[var4];
                if (value == null) {
                    return true;
                }

                if (value instanceof String) {
                    isEmpty = isEmpty((String) value);
                } else if (value instanceof Collection) {
                    isEmpty = isEmpty((Collection) value);
                } else if (value instanceof Map) {
                    isEmpty = isEmpty((Map) value);
                } else if (value.getClass().isArray()) {
                    isEmpty = Array.getLength(value) == 0;
                } else {
                    isEmpty = isEmpty(value);
                }

                if (isEmpty) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean areNotEmpty(Object... values) {
        return !hasEmpty(values);
    }

    public static boolean isEmpty(String value) {
        return Objects.isNull(value) || value.trim().length() == 0;
    }

    public static boolean isEmpty(Collection value) {
        return Objects.isNull(value) || value.isEmpty();
    }

    public static boolean isEmpty(Map value) {
        return Objects.isNull(value) || value.isEmpty();
    }

    public static boolean isEmpty(Object value) {
        return Objects.isNull(value);
    }
}
