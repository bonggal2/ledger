package com.prometheus.ledger.service.facade.admin;

import com.prometheus.ledger.service.facade.admin.request.CheckAdminLoginRequest;
import com.prometheus.ledger.service.facade.admin.result.CheckAdminLoginResult;

public interface AdminFacade {
    CheckAdminLoginResult checkAdminLogin(CheckAdminLoginRequest request);
}
