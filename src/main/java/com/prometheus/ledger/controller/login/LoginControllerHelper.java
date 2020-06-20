package com.prometheus.ledger.controller.login;

import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import org.springframework.ui.Model;

public class LoginControllerHelper {
    public static String buildRedirectPage(CheckLoginResult result, Model model){
        if(null == result || !result.isSuccess() || !result.isExist()){
            model.addAttribute("message", "username or password is wrong");
            return "login";
        }
        model.addAttribute("userId", result.getUserId());
        return "index";
    }
}
