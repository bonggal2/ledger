package com.prometheus.ledger.controller.login;

import com.prometheus.ledger.core.model.Member;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.facade.member.request.RegisterMemberRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

public class LoginControllerHelper {
    public static String buildRedirectPage(CheckLoginResult result, Model model){
        if(null == result || !result.isSuccess() || !result.isExist()){
            model.addAttribute("message", "username or password is wrong");
            return "login";
        }
        model.addAttribute("userId", result.getUserId());
        return "index";
    }

    public static boolean isPasswordSame(String pwd1, String pwd2, Model model){
        boolean result = StringUtil.isEqual(pwd1, pwd2);
        if (!result){
            model.addAttribute("message", "Password must same");
        }
        return result;
    }

    public static RegisterMemberRequest buildRegisterMemberRequest(Map<String, String> body){
        Member member = Member.builder()
                .email(body.get("email"))
                .username(body.get("username"))
                .build();
        Map<String, String> extParams = new HashMap<>();
        extParams.put("password", body.get("password"));

        RegisterMemberRequest registerMemberRequest = RegisterMemberRequest.builder()
                .member(member)
                .build();

        registerMemberRequest.setExtParams(extParams);
        return registerMemberRequest;
    }
}
