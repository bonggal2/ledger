package com.prometheus.ledger.service.impl.member.processor;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Member;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.model.error.ErrorCode;
import com.prometheus.ledger.core.util.AssertUtil;
import com.prometheus.ledger.core.util.EncryptionUtil;
import com.prometheus.ledger.repository.member.MemberRepository;
import com.prometheus.ledger.repository.member.entity.MemberDTO;
import com.prometheus.ledger.service.facade.member.request.RegisterMemberRequest;
import com.prometheus.ledger.service.facade.member.result.RegisterMemberResult;
import org.springframework.beans.factory.annotation.Autowired;


public class RegisterMemberProcessor implements Processor<BaseProcessContext> {

    @Autowired
    private MemberRepository memberRepository;

    private static final String PASSWORD = "password";

    @Override
    public boolean isSkipped(BaseProcessContext context) {
        return false;
    }

    @Override
    public void check(BaseProcessContext context) {
        RegisterMemberRequest request = (RegisterMemberRequest) context.getRequest();
        AssertUtil.isNotNull(request, ErrorCode.PARAM_ILLEGAL, "request is null");
        AssertUtil.isNotNull(request.getMember(), ErrorCode.PARAM_ILLEGAL, "request.member is null");
        AssertUtil.isNotBlank(request.getMember().getUsername(), ErrorCode.PARAM_ILLEGAL, "username is null");
        AssertUtil.isNotNull(request.getExtParams(), ErrorCode.PARAM_ILLEGAL, "extParams is null");
        AssertUtil.isNotBlank(request.getExtParams().get(PASSWORD), ErrorCode.PARAM_ILLEGAL, "extParams is null");
    }

    @Override
    public void doProcess(BaseProcessContext context) {
        RegisterMemberRequest request = (RegisterMemberRequest) context.getRequest();
        RegisterMemberResult result = (RegisterMemberResult) context.getResult();

        MemberDTO memberDTO = memberRepository.save(buildMemberDTO(request));
        AssertUtil.isNotBlank(memberDTO.getMemberId(), ErrorCode.SYSTEM_ERROR, "Register failed");

        result.setUserId(memberDTO.getMemberId());
        result.setSuccess(true);
    }

    private MemberDTO buildMemberDTO(RegisterMemberRequest request){
        String password = EncryptionUtil.sha256Hash(request.getExtParams().get(PASSWORD));
        Member member = request.getMember();
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setEmail(member.getEmail());
        memberDTO.setUsername(member.getUsername());
        memberDTO.setPassword(password);
        return memberDTO;
    }
}
