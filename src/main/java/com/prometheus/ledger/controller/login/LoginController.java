package com.prometheus.ledger.controller.login;

import com.prometheus.ledger.core.util.EncryptionUtil;
import com.prometheus.ledger.core.util.JSONUtil;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.facade.member.MemberFacade;
import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    private static final String SIGN_IN = "Sign In";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Autowired
    private MemberFacade memberFacade;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String loginPage(HttpServletRequest request, HttpServletResponse response, Model model){
        System.out.println(model);
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String loginPost(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Map<String, String> body){
        System.out.println("body: " + JSONUtil.toJsonObject(body));
        String page = "index";
        if (StringUtil.isEqual(body.get("submit"), SIGN_IN)){
            CheckLoginResult result = memberFacade.checkLogin(CheckLoginRequest.builder()
                    .username(body.get(USERNAME))
                    .password(EncryptionUtil.sha256Hash(body.get(PASSWORD)))
                    .build());
            page = LoginControllerHelper.buildRedirectPage(result, model);
        }
        return page;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String registerPage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "register";
    }

}
