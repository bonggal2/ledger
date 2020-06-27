package com.prometheus.ledger.service.impl.admin;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.util.ProcessorUtil;
import com.prometheus.ledger.service.facade.admin.AdminFacade;
import com.prometheus.ledger.service.facade.admin.request.CheckAdminLoginRequest;
import com.prometheus.ledger.service.facade.admin.result.CheckAdminLoginResult;
import com.prometheus.ledger.service.impl.admin.context.AdminContext;

import java.util.List;

public class AdminFacadeImpl implements AdminFacade {
    private List<Processor<BaseProcessContext>> checkAdminLoginProcessors;

    @Override
    public CheckAdminLoginResult checkAdminLogin(CheckAdminLoginRequest request) {
        CheckAdminLoginResult result = new CheckAdminLoginResult();
        AdminContext context = new AdminContext(request, result);
        ProcessorUtil.runProcessors(checkAdminLoginProcessors, context);
        return result;
    }

    public void setCheckAdminLoginProcessors(List<Processor<BaseProcessContext>> checkAdminLoginProcessors) {
        this.checkAdminLoginProcessors = checkAdminLoginProcessors;
    }
}
