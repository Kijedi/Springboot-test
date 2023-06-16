package com.compulynx.test.repos;

import com.compulynx.test.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountId(String accountId);
}
