package com.compulynx.test.controllers;

import com.compulynx.test.repos.AccountRepo;
import com.compulynx.test.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin("*")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountService accountService;

    @GetMapping("/balance")
    Double getAccountBalance(HttpServletRequest req){
        return accountService.getAccount(req).getAccountBalance();
    }

}
