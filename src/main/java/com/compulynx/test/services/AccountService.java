package com.compulynx.test.services;

import com.compulynx.test.models.Account;
import com.compulynx.test.repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class AccountService {

    @Autowired
    JWTService jwtService;

    @Autowired
    AccountRepo accountRepo;

    public Account getAccount(HttpServletRequest req) {
        String requestTokenHeader = req.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            String username = jwtService.getUsernameFromToken(jwtToken);
            if(accountRepo.findAccountByAccountId(username).isPresent())
            {
                return accountRepo.findAccountByAccountId(username).get();
            }
        }
        return null;
    }
}