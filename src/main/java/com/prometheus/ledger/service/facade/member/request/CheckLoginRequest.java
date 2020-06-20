package com.prometheus.ledger.service.facade.member.request;

import com.prometheus.ledger.core.model.request.BaseRequest;
import lombok.Builder;

@Builder
public class CheckLoginRequest extends BaseRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
