package com.prometheus.ledger.controller.main;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController implements ErrorController {

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String mainPage(HttpServletRequest request, HttpServletResponse response, Model model){
        response.setStatus(HttpServletResponse.SC_OK);
        return "index";
    }

    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public String errorPage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "error/error-404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
