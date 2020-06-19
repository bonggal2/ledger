package com.prometheus.ledger.service.impl.account.context;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.request.BaseRequest;
import com.prometheus.ledger.core.model.result.BaseResult;

public class AccountContext extends BaseProcessContext {
    public AccountContext(BaseRequest request, BaseResult result) {
        super(request, result);
    }
}
