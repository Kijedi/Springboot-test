package com.compulynx.test.models;

public class Withdrawal {
    private Double amount;

    public Withdrawal(Double amount) {
        this.amount = amount;
    }

    public Withdrawal() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
