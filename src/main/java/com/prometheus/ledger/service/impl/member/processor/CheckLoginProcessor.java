package com.prometheus.ledger.service.impl.member.processor;

import com.prometheus.ledger.core.model.Member;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.model.error.ErrorCode;
import com.prometheus.ledger.core.util.*;
import com.prometheus.ledger.repository.member.MemberRepository;
import com.prometheus.ledger.repository.member.entity.MemberDTO;
import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import com.prometheus.ledger.service.impl.member.context.CheckLoginContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CheckLoginProcessor implements Processor<CheckLoginContext> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean isSkipped(CheckLoginContext context) {
        return false;
    }

    @Override
    public void check(CheckLoginContext context) {
        CheckLoginRequest request = (CheckLoginRequest) context.getRequest();
        AssertUtil.isNotNull(request, ErrorCode.PARAM_ILLEGAL, "request is null");
        AssertUtil.isNotBlank(request.getUsername(), ErrorCode.PARAM_ILLEGAL, "username is blank");
        AssertUtil.isNotBlank(request.getPassword(), ErrorCode.PARAM_ILLEGAL, "password is blank");
    }

    @Override
    public void doProcess(CheckLoginContext context) {
        CheckLoginRequest request = (CheckLoginRequest) context.getRequest();
        CheckLoginResult result = (CheckLoginResult) context.getResult();

        String password = EncryptionUtil.sha256Hash(request.getPassword());
        List<MemberDTO> dtoList = memberRepository.findByUsernameAndPassword(request.getUsername(), password);
        if(CollectionUtil.isEmpty(dtoList)){
            result.setSuccess(false);
            return;
        }

        System.out.println(JSONUtil.toJsonString(dtoList));

        Member member = dtoList.parallelStream()
                .filter(dto -> StringUtil.isNotBlank(dto.getMemberId()))
                .map(dto -> {
                    System.out.println(JSONUtil.toJsonString(dto));
                    Member member1 = Member.builder()
                            .userId(dto.getMemberId())
                            .username(dto.getUsername())
                            .email(dto.getEmail())
                            .phoneNumber(dto.getPhonenumber())
                            .build();
                    return member1;
                }).findFirst().orElse(null);

        System.out.println(JSONUtil.toJsonString(member));

        AssertUtil.isNotNull(member, ErrorCode.SYSTEM_ERROR, "memberIsNull");
        result.setUserId(member.getUserId());
        result.setExist(true);
        result.setSuccess(true);
    }
}
