package com.prometheus.ledger.service.common.session;

import com.prometheus.ledger.service.common.session.result.GetLoginSessionResult;

import javax.servlet.http.HttpSession;

public interface SessionService {
    boolean saveLoginSession(HttpSession session, String userId);
    GetLoginSessionResult getLoginSession(HttpSession session);
    boolean saveAdminLoginSession(HttpSession session, String adminId);
}
