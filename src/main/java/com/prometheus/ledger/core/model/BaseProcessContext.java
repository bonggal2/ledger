package com.prometheus.ledger.core.model;

import com.prometheus.ledger.core.model.request.BaseRequest;
import com.prometheus.ledger.core.model.result.BaseResult;

public class BaseProcessContext extends ProcessContext<BaseRequest, BaseResult>{

    public BaseProcessContext(BaseRequest request, BaseResult result) {
        super(request, result);
    }
}
