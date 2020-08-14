package com.prometheus.ledger.service.impl.account;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.util.ProcessorUtil;
import com.prometheus.ledger.service.facade.account.AccountFacade;
import com.prometheus.ledger.service.facade.account.request.QueryAccountListRequest;
import com.prometheus.ledger.service.facade.account.result.QueryAccountListResult;
import com.prometheus.ledger.service.impl.account.context.AccountContext;

import java.util.List;

public class AccountFacadeImpl implements AccountFacade {

    private static List<Processor<BaseProcessContext>> queryAccountListProcessors;

    @Override
    public QueryAccountListResult queryAccount(QueryAccountListRequest request) {
        QueryAccountListResult result = new QueryAccountListResult();
        AccountContext context = new AccountContext(request, result);
        ProcessorUtil.runProcessors(queryAccountListProcessors, context);
        return result;
    }

    public void setQueryAccountListProcessors(List<Processor<BaseProcessContext>> queryAccountListProcessors) {
        AccountFacadeImpl.queryAccountListProcessors = queryAccountListProcessors;
    }
}
