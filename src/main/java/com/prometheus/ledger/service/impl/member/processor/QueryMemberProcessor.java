package com.prometheus.ledger.service.impl.member.processor;

import com.prometheus.ledger.core.model.Member;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.model.error.ErrorCode;
import com.prometheus.ledger.core.util.AssertUtil;
import com.prometheus.ledger.repository.member.MemberRepository;
import com.prometheus.ledger.repository.member.entity.MemberDTO;
import com.prometheus.ledger.service.facade.member.request.QueryMemberRequest;
import com.prometheus.ledger.service.facade.member.result.QueryMemberResult;
import com.prometheus.ledger.service.impl.member.context.QueryMemberContext;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryMemberProcessor implements Processor<QueryMemberContext> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean isSkipped(QueryMemberContext context) {
        return false;
    }

    @Override
    public void check(QueryMemberContext context) {

    }

    @Override
    public void doProcess(QueryMemberContext context) {
        QueryMemberRequest request = (QueryMemberRequest) context.getRequest();
        QueryMemberResult result = (QueryMemberResult) context.getResult();

        MemberDTO memberDTO = memberRepository.findById(request.getUserId()).get();
        AssertUtil.isNotNull(memberDTO, ErrorCode.SYSTEM_ERROR, "memberDTO is null");

        convertToResult(memberDTO, result);
    }

    private void convertToResult(MemberDTO memberDTO, QueryMemberResult result){
        Member member = new Member();
        member.setUserId(memberDTO.getMemberId());
        member.setUsername(memberDTO.getUsername());
        member.setEmail(memberDTO.getEmail());
        member.setPhoneNumber(memberDTO.getPhonenumber());

        result.setMember(member);
        result.setSuccess(true);
    }
}
