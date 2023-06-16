package com.compulynx.test.services;

import com.compulynx.test.models.Account;
import com.compulynx.test.models.Transaction;
import com.compulynx.test.models.TransactionType;
import com.compulynx.test.repos.AccountRepo;
import com.compulynx.test.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    TransactionRepo transactionRepo;

    public Account fundTransfer(Account account, String accountB, Double amount) {
        Optional<Account> account_B = accountRepo.findAccountByAccountId(accountB);
        //Todo: Check if the Account A has enough balance to warrant transaction
            if (account.getAccountBalance() > amount) {
                account.setAccountBalance(account.getAccountBalance() - amount);
                if (account_B.isPresent()) {
                    Account account1 = account_B.get();
                    account1.setAccountBalance(account1.getAccountBalance() + amount);
                    transactionRepo.save(
                            new Transaction(UUID.randomUUID().toString(), TransactionType.Debit, account1.getAccountId(), new Timestamp(System.currentTimeMillis())));
                    accountRepo.save(account1);
                    //Todo: Save transaction for account B
                }
                transactionRepo.save(
                        new Transaction(UUID.randomUUID().toString(), TransactionType.Credit, account.getAccountId(), new Timestamp(System.currentTimeMillis())));
                return accountRepo.save(account);
                //Todo: Save transaction for account A
            }
        return null;
    }

    public Account cashWithdrawal(String accountID, Double amount) {
        Optional<Account> account = accountRepo.findAccountByAccountId(accountID);
        if (account.isPresent() && account.get().getAccountBalance() > amount) {
            Account account1 = account.get();
            account1.setAccountBalance(account1.getAccountBalance() - amount);
            transactionRepo.save(new Transaction(UUID.randomUUID().toString(), TransactionType.Credit, accountID, new Timestamp(System.currentTimeMillis())));
            return accountRepo.save(account1);
        }
        return null;
    }

    public Account cashDeposit(String accountID, Double amount) {
        Optional<Account> account2 = accountRepo.findAccountByAccountId(accountID);
        if (account2.isPresent()) {
            Account account1 = account2.get();
            account1.setAccountBalance(account1.getAccountBalance() + amount);
            transactionRepo.save(new Transaction(UUID.randomUUID().toString(), TransactionType.Debit, accountID, new Timestamp(System.currentTimeMillis())));
            return accountRepo.save(account1);
        }
        return null;
    }
}