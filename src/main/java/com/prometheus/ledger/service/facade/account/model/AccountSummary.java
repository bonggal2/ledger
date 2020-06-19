package com.prometheus.ledger.service.facade.account.model;

import com.prometheus.ledger.core.model.BaseModel;
import com.prometheus.ledger.core.model.Money;

public class AccountSummary extends BaseModel {
    private String AccountId;
    private String AccountName;
    private Money AccountBalance;

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public Money getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(Money accountBalance) {
        AccountBalance = accountBalance;
    }
}
