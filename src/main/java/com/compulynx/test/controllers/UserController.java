package com.compulynx.test.controllers;

import com.compulynx.test.models.Account;
import com.compulynx.test.models.AuthRequest;
import com.compulynx.test.models.Customer;
import com.compulynx.test.repos.AccountRepo;
import com.compulynx.test.repos.CustomerRepo;
import com.compulynx.test.services.CustomerDetailsService;
import com.compulynx.test.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CustomerDetailsService customerDetailsService;
    @Autowired
    AccountRepo accountRepo;

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = customerDetailsService.loadUserByUsername(authRequest.getUsername());
            return jwtService.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        if (customerRepo.findByCustomerEmail(customer.getCustomerEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            int password = new Random().nextInt(9000) + 1000;
            customer.setCustomerPIN(new BCryptPasswordEncoder().encode(String.valueOf(password)));
            customer.setCustomerId(String.valueOf(new Random().nextInt(9000) + 1000));
            Customer registeredCustomer = customerRepo.save(customer);
            accountRepo.save(new Account(registeredCustomer.getCustomerId(), 0.0));
            registeredCustomer.setCustomerPIN(String.valueOf(password));
            return new ResponseEntity<>(registeredCustomer,
                    HttpStatus.OK);
        }
    }
}


//    @PostMapping("/user/authenticate")
//    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
//            throws Exception {
//        String email_phone = authenticationRequest.getUsername();
//        System.out.println(authenticationRequest.getUsername());
//        System.out.println(authenticationRequest.getPassword());
//        Authentication authentication = authenticate(email_phone, authenticationRequest.getPassword());
//        System.out.println(authentication.isAuthenticated());
//
//        if (authentication.isAuthenticated()) {
//            System.out.println("yes we are in");
//            final UserDetails userDetails = userDetailsService
//                    .loadUserByUsername(authenticationRequest.getUsername());
//            final String token = jwtTokenUtil.generateToken(userDetails);
//            System.out.println(token);
//            User user = userRepository.findByEmail(authenticationRequest.getUsername()).get();
//            user.setPassword(token);
//            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    private Authentication authenticate(String username, String password) throws Exception {
//        Objects.requireNonNull(username);
//        Objects.requireNonNull(password);
//        try {
//            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
