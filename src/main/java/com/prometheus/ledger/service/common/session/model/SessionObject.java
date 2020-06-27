package com.prometheus.ledger.service.common.session.model;

import com.prometheus.ledger.core.model.BaseModel;

public class SessionObject extends BaseModel {
    private String userId;
    private boolean isAdmin;
    private long timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
