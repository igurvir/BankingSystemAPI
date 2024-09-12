package com.bankingsystem.bankingsystem2.service;

import com.bankingsystem.bankingsystem2.model.Account;
import com.bankingsystem.bankingsystem2.model.Transaction;
import com.bankingsystem.bankingsystem2.repository.AccountRepository;
import com.bankingsystem.bankingsystem2.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Get all transactions for a specific account by ID
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // Make a transaction (deposit or withdraw)
    public Transaction makeTransaction(Long accountId, Transaction transaction) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            Account accountEntity = account.get();
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setAccount(accountEntity);

            // Update account balance based on transaction type
            if ("deposit".equalsIgnoreCase(transaction.getTransactionType())) {
                accountEntity.setBalance(accountEntity.getBalance() + transaction.getAmount());
            } else if ("withdraw".equalsIgnoreCase(transaction.getTransactionType())) {
                if (accountEntity.getBalance() >= transaction.getAmount()) {
                    accountEntity.setBalance(accountEntity.getBalance() - transaction.getAmount());
                } else {
                    throw new RuntimeException("Insufficient balance for withdrawal");
                }
            }

            // Save the updated account balance
            accountRepository.save(accountEntity);

            // Save and return the transaction
            return transactionRepository.save(transaction);
        }
        return null; // Account not found
    }
}
