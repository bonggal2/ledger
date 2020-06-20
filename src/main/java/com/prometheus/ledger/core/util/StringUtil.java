package com.prometheus.ledger.core.util;

public class StringUtil {
    public static final String EMPTY_STRING = "";

    public static boolean isEqual(String str1, String str2){
        if (null == str1){
            return null == str2;
        }

        return str1.equals(str2);
    }

    public static boolean isNotEqual(String str1, String str2){
        return !isEqual(str1, str2);
    }

    public static boolean isNotBlank(String str){
        if (null == str){
            return false;
        } else if (str.length() == 0){
            return false;
        }

        return true;
    }

    public static boolean isBlank(String str){
        return !isNotBlank(str);
    }
}
