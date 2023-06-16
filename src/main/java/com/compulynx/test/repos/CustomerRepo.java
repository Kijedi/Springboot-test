package com.compulynx.test.repos;

import com.compulynx.test.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerEmail(String email);
    Optional<Customer> findByCustomerId(String customerId);

}
