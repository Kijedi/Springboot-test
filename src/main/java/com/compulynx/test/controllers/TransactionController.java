package com.compulynx.test.controllers;

import com.compulynx.test.models.*;
import com.compulynx.test.repos.TransactionRepo;
import com.compulynx.test.services.AccountService;
import com.compulynx.test.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @GetMapping("/statement")
    List<Transaction> getLast10Transactions(HttpServletRequest req) {
        String accountId = accountService.getAccount(req).getAccountId();
        return transactionRepo.findFirst10ByAccountIdOrderByTimestampDesc(accountId);
    }

    @PostMapping("/deposit")
    Double cashDeposit(@RequestBody Deposit deposit, HttpServletRequest req) {
        String accountId = accountService.getAccount(req).getAccountId();
        return transactionService.cashDeposit(accountId, deposit.getAmount()).getAccountBalance();
    }

    @PostMapping("/withdrawal")
    Double cashWithdrawal(@RequestBody Withdrawal withdrawal, HttpServletRequest req) {
        String accountId = accountService.getAccount(req).getAccountId();
        return transactionService.cashWithdrawal(accountId, withdrawal.getAmount()).getAccountBalance();
    }

    @PostMapping ("/transfer")
    Double cashTransfer(@RequestBody Transfer transfer, HttpServletRequest req) {
        Account account_A = accountService.getAccount(req);
        return transactionService.fundTransfer(account_A, transfer.getToAccount(), transfer.getAmount()).getAccountBalance();
    }

    @GetMapping("/search/{id}")
    Transaction searchTransaction(@PathVariable("id") String id) {
        return transactionRepo.findTransactionByTransactionId(id).get();
    }
}
