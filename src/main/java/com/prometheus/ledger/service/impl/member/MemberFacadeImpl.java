package com.prometheus.ledger.service.impl.member;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.util.ProcessorUtil;
import com.prometheus.ledger.service.facade.member.MemberFacade;
import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.request.QueryMemberRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import com.prometheus.ledger.service.facade.member.result.QueryMemberResult;
import com.prometheus.ledger.service.impl.member.context.CheckLoginContext;
import com.prometheus.ledger.service.impl.member.context.QueryMemberContext;

import java.util.List;

public class MemberFacadeImpl implements MemberFacade {
    private List<Processor<BaseProcessContext>> queryMemberProcessors;
    private List<Processor<BaseProcessContext>> checkLoginProcessors;

    @Override
    public QueryMemberResult queryMember(QueryMemberRequest request) {
        QueryMemberResult result = new QueryMemberResult();
        QueryMemberContext context = new QueryMemberContext(request, result);
        ProcessorUtil.runProcessors(queryMemberProcessors, context);
        return result;
    }

    @Override
    public CheckLoginResult checkLogin(CheckLoginRequest request) {
        CheckLoginResult result = new CheckLoginResult();
        CheckLoginContext context = new CheckLoginContext(request, result);
        ProcessorUtil.runProcessors(checkLoginProcessors, context);
        return result;
    }

    public void setQueryMemberProcessors(List<Processor<BaseProcessContext>> queryMemberProcessors) {
        this.queryMemberProcessors = queryMemberProcessors;
    }

    public void setCheckLoginProcessors(List<Processor<BaseProcessContext>> checkLoginProcessors) {
        this.checkLoginProcessors = checkLoginProcessors;
    }
}
