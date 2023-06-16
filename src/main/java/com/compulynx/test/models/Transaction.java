package com.compulynx.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;
    private String transactionId;
    private TransactionType transactionType;
    private String accountId;
    private Timestamp timestamp;

    public Transaction(String transactionId, TransactionType transactionType, String accountId, Timestamp timestamp) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.timestamp = timestamp;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transaction_id) {
        this.transactionId = transaction_id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountID) {
        this.accountId = accountID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}


