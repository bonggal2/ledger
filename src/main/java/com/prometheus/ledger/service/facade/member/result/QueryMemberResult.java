package com.prometheus.ledger.service.facade.member.result;

import com.prometheus.ledger.core.model.Member;
import com.prometheus.ledger.core.model.result.BaseResult;

public class QueryMemberResult extends BaseResult {
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
