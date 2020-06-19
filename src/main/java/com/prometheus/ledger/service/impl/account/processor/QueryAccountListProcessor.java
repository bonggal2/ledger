package com.prometheus.ledger.service.impl.account.processor;

import com.prometheus.ledger.core.model.Money;
import com.prometheus.ledger.core.model.Processor;
import com.prometheus.ledger.core.util.CollectionUtil;
import com.prometheus.ledger.repository.account.AccountRepository;
import com.prometheus.ledger.repository.account.entity.AccountDTO;
import com.prometheus.ledger.service.facade.account.model.AccountSummary;
import com.prometheus.ledger.service.facade.account.result.QueryAccountListResult;
import com.prometheus.ledger.service.impl.account.context.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class QueryAccountListProcessor implements Processor<AccountContext> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean isSkipped(AccountContext context) {
        return false;
    }

    @Override
    public void check(AccountContext context) {

    }

    @Override
    public void doProcess(AccountContext context) {
        QueryAccountListResult result = (QueryAccountListResult) context.getResult();
        List<AccountDTO> accountDTOList = accountRepository.findAll();

        if (CollectionUtil.isEmpty(accountDTOList)){
            result.setSuccess(true);
            return;
        }

        List<AccountSummary> accountSummaryList = accountDTOList.parallelStream().map(dto ->{
            AccountSummary summary = new AccountSummary();
            summary.setAccountId(dto.getAccountId());
            summary.setAccountName(dto.getAccountName());

            Money balance = new Money();
            balance.setCurrency(dto.getCurrency());
            balance.setAmount(dto.getAccountBalance());
            summary.setAccountBalance(balance);

            return summary;
        }).collect(Collectors.toList());

        result.setAccountList(accountSummaryList);
        result.setSuccess(true);
    }
}
