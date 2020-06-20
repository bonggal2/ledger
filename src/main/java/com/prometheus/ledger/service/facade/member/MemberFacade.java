package com.prometheus.ledger.service.facade.member;

import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.request.QueryMemberRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import com.prometheus.ledger.service.facade.member.result.QueryMemberResult;

public interface MemberFacade {
    QueryMemberResult queryMember(QueryMemberRequest request);
    CheckLoginResult checkLogin(CheckLoginRequest request);
}
