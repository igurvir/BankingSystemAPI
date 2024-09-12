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
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            
            // Ensure accountType is loaded
            if (account.getAccountType() == null) {
                throw new RuntimeException("Account type is not set for this account.");
            }
            
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setAccount(account);

            // Update account balance based on transaction type
            if ("deposit".equalsIgnoreCase(transaction.getTransactionType())) {
                account.setBalance(account.getBalance() + transaction.getAmount());
            } else if ("withdraw".equalsIgnoreCase(transaction.getTransactionType())) {
                if (account.getBalance() >= transaction.getAmount()) {
                    account.setBalance(account.getBalance() - transaction.getAmount());
                } else {
                    throw new RuntimeException("Insufficient balance for withdrawal");
                }
            }

            // Save the updated account balance
            accountRepository.save(account);

            // Save and return the transaction
            return transactionRepository.save(transaction);
        }
        return null; // Account not found
    }
}
