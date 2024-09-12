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
    public ResponseEntity<?> makeTransaction(@PathVariable Long accountId, @RequestBody Transaction transaction) {
        // Validate transaction type
        if (transaction.getTransactionType() == null || (!"deposit".equalsIgnoreCase(transaction.getTransactionType())
                && !"withdraw".equalsIgnoreCase(transaction.getTransactionType()))) {
            return ResponseEntity.status(400).body("Invalid transaction type. Use 'deposit' or 'withdraw'.");
        }

        // Validate amount
        if (transaction.getAmount() <= 0) {
            return ResponseEntity.status(400).body("Transaction amount must be greater than zero.");
        }

        try {
            Transaction savedTransaction = transactionService.makeTransaction(accountId, transaction);
            return ResponseEntity.ok(savedTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
