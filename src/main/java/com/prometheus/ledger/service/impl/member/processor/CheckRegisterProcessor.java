/**
 * DANA Indonesia.
 * Copyright (c) 2004‐2020 All Rights Reserved.
 */
package com.prometheus.ledger.service.impl.member.processor;

import com.prometheus.ledger.core.model.BaseProcessContext;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.model.error.ErrorCode;
import com.prometheus.ledger.core.util.AssertUtil;
import com.prometheus.ledger.repository.member.MemberRepository;
import com.prometheus.ledger.repository.member.entity.MemberDTO;
import com.prometheus.ledger.service.facade.member.request.RegisterMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author bonggal.siahaan
 * @version $Id: CheckRegisterProcessor.java, v 0.1 2020‐09‐13 00.30 bonggal.siahaan Exp $$ */
public class CheckRegisterProcessor implements Processor<BaseProcessContext> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean isSkipped(BaseProcessContext context) {
        return false;
    }

    @Override
    public void check(BaseProcessContext context) {
        RegisterMemberRequest request = (RegisterMemberRequest) context.getRequest();
        AssertUtil.isNotBlank(request.getMember().getUsername(), ErrorCode.PARAM_ILLEGAL, "username is null");
    }

    @Override
    public void doProcess(BaseProcessContext context) {
        RegisterMemberRequest request = (RegisterMemberRequest) context.getRequest();
        MemberDTO memberDTO = memberRepository.findByUsername(request.getMember().getUsername());
        AssertUtil.isNull(memberDTO, ErrorCode.SYSTEM_ERROR,
                "member already exist, username: " + request.getMember().getUsername());
    }
}
