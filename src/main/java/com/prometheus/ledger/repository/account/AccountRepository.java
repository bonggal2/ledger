package com.prometheus.ledger.repository.account;

import com.prometheus.ledger.repository.account.entity.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO, String> {
}
