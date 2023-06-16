package com.compulynx.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId;
    private String customerEmail;
    private String customerPIN;

    public Customer() {
    }

    public Customer(String customerId,  String customerEmail, String customerPIN) {
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.customerPIN = customerPIN;
    }

    public Customer(String customerEmail){
        this.customerEmail = customerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customer_id) {
        this.customerId = customer_id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customer_email) {
        this.customerEmail = customer_email;
    }

    public String getCustomerPIN() {
        return customerPIN;
    }

    public void setCustomerPIN(String custoemr_PIN) {
        this.customerPIN = custoemr_PIN;
    }
}
