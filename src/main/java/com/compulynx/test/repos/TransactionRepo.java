package com.compulynx.test.repos;

import com.compulynx.test.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTransactionByTransactionId(String transaction_id);
    List<Transaction> findFirst10ByAccountIdOrderByTimestampDesc(String accountId);
}
