package com.prometheus.ledger.core.util;

import com.prometheus.ledger.core.model.error.BaseErrorCode;

import java.util.Collection;

public class AssertUtil {
    public static void isNotNull(Object object, BaseErrorCode errorCode, String message){
        try {
            String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                    + " - " + message;
            if (null == object) {
                throw new Exception(exceptionMessage);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void isNull(Object object, BaseErrorCode errorCode, String message) {
        try {
            String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                    + " - " + message;
            if (null != object) {
                throw new Exception(exceptionMessage);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void isNotBlank(String string, BaseErrorCode errorCode, String message){
        try{
            String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                    + " - " + message;
            if (StringUtil.isBlank(string)){
                throw new Exception(exceptionMessage);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void isBlank(String string, BaseErrorCode errorCode, String message){
        try{
            String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                    + " - " + message;
            if (StringUtil.isNotBlank(string)){
                throw new Exception(exceptionMessage);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void isNotEmpty(Collection collection, BaseErrorCode errorCode, String message){
        try{
            String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                    + " - " + message;
            if (CollectionUtil.isEmpty(collection)){
                throw new Exception(exceptionMessage);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
