package com.bankingsystem.bankingsystem2.controller;

import com.bankingsystem.bankingsystem2.model.Transaction;
import com.bankingsystem.bankingsystem2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get transactions for a specific account
    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Make a transaction (deposit/withdrawal)
    @PostMapping("/{accountId}")
    public ResponseEntity<Transaction> makeTransaction(@PathVariable Long accountId, @RequestBody Transaction transaction) {
        try {
            Transaction savedTransaction = transactionService.makeTransaction(accountId, transaction);
            if (savedTransaction != null) {
                return ResponseEntity.ok(savedTransaction);
            } else {
                return ResponseEntity.badRequest().body(null); // Account not found or transaction failed
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Insufficient balance or other runtime errors
        }
    }
}
