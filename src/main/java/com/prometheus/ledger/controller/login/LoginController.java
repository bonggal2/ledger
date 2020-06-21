package com.prometheus.ledger.controller.login;

import com.prometheus.ledger.core.util.EncryptionUtil;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.common.session.SessionService;
import com.prometheus.ledger.service.facade.member.MemberFacade;
import com.prometheus.ledger.service.facade.member.request.CheckLoginRequest;
import com.prometheus.ledger.service.facade.member.result.CheckLoginResult;
import com.prometheus.ledger.service.facade.member.result.RegisterMemberResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String loginPage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String loginPost(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Map<String, String> body){
        String page = "login";
        if (StringUtil.isEqual(body.get("submit"), SIGN_IN)){
            CheckLoginResult result = memberFacade.checkLogin(CheckLoginRequest.builder()
                    .username(body.get(USERNAME))
                    .password(EncryptionUtil.sha256Hash(body.get(PASSWORD)))
                    .build());

            if (null!=result && result.isSuccess() && result.isExist() && StringUtil.isNotBlank(result.getUserId())){
                boolean isSessionSuccess = sessionService.saveLoginSession(request.getSession(), result.getUserId());
                if (!isSessionSuccess){
                    return page;
                }

            }

            page = LoginControllerHelper.buildRedirectPage(result, model);
        }
        return "redirect:"+page;
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
                return "redirect:/"+page;
            }

            RegisterMemberResult result = memberFacade.registerMember(LoginControllerHelper.buildRegisterMemberRequest(body));
            if (result.isSuccess()){
                page = "index";
                sessionService.saveLoginSession(request.getSession(), result.getUserId());
            }
        }
        return "redirect:/"+page;
    }

    // this method is to avoid browser return 404
    @RequestMapping(value = "/favicon.ico") @ResponseBody
    public void disableFavicon(){}

}
