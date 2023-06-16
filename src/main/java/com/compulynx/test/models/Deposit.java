package com.compulynx.test.models;

public class Deposit {
    private Double amount;

    public Deposit(Double amount) {
        this.amount = amount;
    }

    public Deposit() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
