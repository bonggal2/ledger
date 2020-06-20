package com.prometheus.ledger.service.facade.member.result;

import com.prometheus.ledger.core.model.result.BaseResult;

public class CheckLoginResult extends BaseResult {
    private boolean isExist;
    private String userId;

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
