package com.prometheus.ledger.service.facade.account.result;

import com.prometheus.ledger.core.model.result.BaseResult;
import com.prometheus.ledger.service.facade.account.model.AccountSummary;

import java.util.List;

public class QueryAccountListResult extends BaseResult {
    private List<AccountSummary> accountList;

    public List<AccountSummary> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountSummary> accountList) {
        this.accountList = accountList;
    }
}
