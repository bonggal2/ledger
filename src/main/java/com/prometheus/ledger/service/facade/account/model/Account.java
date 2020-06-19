package com.prometheus.ledger.service.facade.account.model;

import com.prometheus.ledger.core.model.BaseModel;
import com.prometheus.ledger.core.model.Money;

public class Account extends BaseModel {
    private String accountId;
    private String accountName;
    private Money accountBalance;
    private String accountDesc;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Money getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Money accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }
}
