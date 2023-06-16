package com.compulynx.test.services;


import com.compulynx.test.models.Customer;
import com.compulynx.test.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String customer_id) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepo.findByCustomerId(customer_id);
        if (customer.isPresent()) return customer.map(CustomerDetails::new).get();
        else new UsernameNotFoundException(customer + " not found.");
        return null;
    }
}
