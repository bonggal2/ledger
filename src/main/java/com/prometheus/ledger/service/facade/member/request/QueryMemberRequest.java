package com.prometheus.ledger.service.facade.member.request;

import com.prometheus.ledger.core.model.request.BaseRequest;
import lombok.Builder;

@Builder
public class QueryMemberRequest extends BaseRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
