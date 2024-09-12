package com.bankingsystem.bankingsystem2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference  // Prevent recursion, user will manage the reference
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    @JsonIgnoreProperties({"accounts"})  // Ignore accounts in AccountType to prevent recursion
    private AccountType accountType;

    // Constructor
    public Account() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
