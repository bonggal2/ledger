package com.prometheus.ledger.core.model.error;

import java.util.Map;

public class ErrorContext implements BaseErrorContext {
    private BaseErrorCode errorCode;
    private Map<String, String> extendErrorInfo;

    public String getErrorMessage() {
        return errorCode.getErrorMessage();
    }

    @Override
    public String getErrorCode() {
        return errorCode.getErrorCode();
    }

    public void setErrorCode(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getExtendErrorInfo() {
        return extendErrorInfo;
    }

    public void setExtendErrorInfo(Map<String, String> extendErrorInfo) {
        this.extendErrorInfo = extendErrorInfo;
    }

    public void addExtendErrorInfo(String key, String value){
        this.extendErrorInfo.put(key, value);
    }
}
