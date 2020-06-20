package com.prometheus.ledger.service.facade.member.request;

import com.prometheus.ledger.core.model.Member;
import com.prometheus.ledger.core.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequest extends BaseRequest {
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
