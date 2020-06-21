package com.prometheus.ledger.controller.ledger;

import com.prometheus.ledger.core.util.StringUtil;
import com.prometheus.ledger.service.common.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ledger")
public class LedgerController {

    @Autowired
    private SessionService sessionService;

    private static final String TOTAL_EXPENSES = "totalExpenses";

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String ledgerMainPage(HttpServletRequest request, HttpServletResponse response, Model model){
        if(StringUtil.isBlank(sessionService.getLoginSession(request.getSession()).getUserId())){
            return "redirect:/login";
        }

        // getTotalExpenses
        // getTotalIncomes
        // getCategorizeExpenses
        // getCategorizeIncomes
        model.addAttribute(TOTAL_EXPENSES, "");

        response.setStatus(HttpServletResponse.SC_OK);
        return "ledger/home";
    }
}
