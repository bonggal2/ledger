package com.prometheus.ledger.controller.ledger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ledger")
public class LedgerController {

    private static final String TOTAL_EXPENSES = "totalExpenses";

    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String ledgerMainPage(HttpServletRequest request, HttpServletResponse response, Model model){

        // getTotalExpenses
        // getTotalIncomes
        // getCategorizeExpenses
        // getCategorizeIncomes
        model.addAttribute(TOTAL_EXPENSES, "");

        response.setStatus(HttpServletResponse.SC_OK);
        return "ledger/home";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountPage(HttpServletRequest request, HttpServletResponse response, Model model){

        // getTotalExpenses
        // getTotalIncomes
        // getCategorizeExpenses
        // getCategorizeIncomes
        model.addAttribute(TOTAL_EXPENSES, "");

        response.setStatus(HttpServletResponse.SC_OK);
        return "ledger/home";
    }
}
