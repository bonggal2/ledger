package com.prometheus.ledger.service.facade.member.result;

import com.prometheus.ledger.core.model.result.BaseResult;

public class RegisterMemberResult extends BaseResult {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
