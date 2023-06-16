package com.compulynx.test.models;

public class Transfer {
    private String toAccount;
    private Double amount;

    public Transfer(String toAccount, Double amount) {
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public Transfer() {
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
