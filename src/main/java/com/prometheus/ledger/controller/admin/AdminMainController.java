package com.prometheus.ledger.controller.admin;

import com.prometheus.ledger.core.model.error.ErrorCode;
import com.prometheus.ledger.core.util.AssertUtil;
import com.prometheus.ledger.core.util.EncryptionUtil;
import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.common.session.SessionService;
import com.prometheus.ledger.service.facade.admin.AdminFacade;
import com.prometheus.ledger.service.facade.admin.request.CheckAdminLoginRequest;
import com.prometheus.ledger.service.facade.admin.result.CheckAdminLoginResult;
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
@RequestMapping(value = "/admin")
public class AdminMainController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdminFacade adminFacade;

    private static final String SIGN_IN = "Sign In";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String adminLoginPage(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "admin/login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String adminLoginPost(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Map<String, String> body) throws Throwable {
        if (StringUtil.isEqual(body.get("submit"), SIGN_IN)) {

            CheckAdminLoginResult result = adminFacade.checkAdminLogin(buildCheckAdminLoginRequest(body));
            if (null == result || !result.isSuccess() || StringUtil.isBlank(result.getAdminId())){
                model.addAttribute("message", "Username or password is wrong");
                return adminLoginPage(request, response, model);
            }

            boolean isSessionSuccess = sessionService.saveLoginSession(request.getSession(), result.getAdminId());
            if (!isSessionSuccess) {
                model.addAttribute("message", "Something is wrong, please try again");
                return adminLoginPage(request, response, model);
            }

        }
        response.sendRedirect("admin/home");
        return "redirect:/admin/home";
    }

    private CheckAdminLoginRequest buildCheckAdminLoginRequest(Map<String, String> body) {
        AssertUtil.isNotNull(body, ErrorCode.PARAM_ILLEGAL, "body is null");
        AssertUtil.isNotBlank(body.get(USERNAME), ErrorCode.PARAM_ILLEGAL, "param username is blank");
        AssertUtil.isNotBlank(body.get(PASSWORD), ErrorCode.PARAM_ILLEGAL, "param password is blank");
        return CheckAdminLoginRequest.builder()
                .username(body.get(USERNAME))
                .password(EncryptionUtil.sha256Hash(body.get(PASSWORD)))
                .build();
    }
}
