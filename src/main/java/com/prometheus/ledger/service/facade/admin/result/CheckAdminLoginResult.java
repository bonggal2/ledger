package com.prometheus.ledger.service.facade.admin.result;

import com.prometheus.ledger.core.model.result.BaseResult;

public class CheckAdminLoginResult extends BaseResult {
    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
