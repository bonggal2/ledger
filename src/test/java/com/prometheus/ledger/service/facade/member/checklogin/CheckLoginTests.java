/**
 * Alipay.com Inc.
 * Copyright (c) 2004‐2020 All Rights Reserved.
 */
package com.prometheus.ledger.service.facade.member.checklogin;

import com.prometheus.ledger.LedgerApplicationTests;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.repository.member.MemberRepository;
import com.prometheus.ledger.service.facade.member.MemberFacade;
import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.BDDMockito.given;

/**
 * @author Bonggal Siahaan (bonggal.siahaan@dana.id)
 * @version $Id: CheckLoginTests.java, v 0.1 2020‐08‐14 17.03 bonggalsiahaan Exp $$ */
public class CheckLoginTests extends LedgerApplicationTests{

    @Autowired
    private MemberFacade memberFacade;
    @Mock
    private MemberRepository memberRepository;

    @Before
    public void setup(){
//        memberRepository = mock(MemberRepository.class);
    }

    @Test
    public void checkLogin_success(){
        CheckLoginRequest request = CheckLoginRequest.builder()
                .username("TestUserName")
                .password("TestPassword")
                .build();

        given(memberRepository.findByUsernameAndPassword("",""))
                .willReturn(null);
        CheckLoginResult result = memberFacade.checkLogin(request);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.isSuccess());
        Assert.assertTrue(StringUtil.isNotBlank(result.getUserId()));
        Assert.assertTrue(result.isExist());
    }
}