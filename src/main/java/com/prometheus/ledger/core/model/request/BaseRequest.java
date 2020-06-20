package com.prometheus.ledger.core.model.request;

import com.prometheus.ledger.core.model.BaseModel;

import java.util.Map;

public class BaseRequest extends BaseModel {
    private Map<String, String> extParams;

    public Map<String, String> getExtParams() {
        return extParams;
    }

    public void setExtParams(Map<String, String> extParams) {
        this.extParams = extParams;
    }
}
