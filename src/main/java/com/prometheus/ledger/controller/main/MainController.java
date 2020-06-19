package com.prometheus.ledger.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String mainPage(HttpServletRequest request, HttpServletResponse response, Model model){
        response.setStatus(HttpServletResponse.SC_OK);
        return "index";
    }

}
