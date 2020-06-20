package com.prometheus.ledger.controller.login;

import com.prometheus.ledger.core.util.EncryptionUtil;
import com.prometheus.ledger.core.util.JSONUtil;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.facade.member.MemberFacade;
import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import com.prometheus.ledger.service.facade.member.result.RegisterMemberResult;
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
    private static final String REGISTER = "Register";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Autowired
    private MemberFacade memberFacade;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String loginPage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String loginPost(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Map<String, String> body){
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

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String registerPost(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Map<String, String> body){
        String page = "register";

        if (StringUtil.isEqual(body.get("submit"), REGISTER)){
            boolean pwd = LoginControllerHelper.isPasswordSame(body.get("password"), body.get("repassword"), model);
            if (!pwd){
                return page;
            }

            RegisterMemberResult result = memberFacade.registerMember(LoginControllerHelper.buildRegisterMemberRequest(body));
            if (result.isSuccess()){
                page = "index";
            }
        }
        return page;
    }

}
