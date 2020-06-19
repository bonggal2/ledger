package com.prometheus.ledger.core.util;

import com.prometheus.ledger.core.model.error.BaseErrorCode;

public class AssertUtil {
    public void isNotNull(Object object, BaseErrorCode errorCode, String message) throws Throwable{
        String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                + " - " + message;
        if(null == object){
            throw new Exception(exceptionMessage);
        }
    }

    public void isNull(Object object, BaseErrorCode errorCode, String message) throws Throwable{
        String exceptionMessage = errorCode.getErrorCode() + ":" + errorCode.getErrorMessage()
                + " - " + message;
        if(null != object){
            throw new Exception(exceptionMessage);
        }
    }
}
