package com.prometheus.ledger.service.impl.admin.context;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.request.BaseRequest;
import com.prometheus.ledger.core.model.result.BaseResult;

public class AdminContext extends BaseProcessContext {
    public AdminContext(BaseRequest request, BaseResult result) {
        super(request, result);
    }
}
