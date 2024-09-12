package com.bankingsystem.bankingsystem.service;

import com.bankingsystem.bankingsystem.model.Account;
import com.bankingsystem.bankingsystem.model.Transaction;
import com.bankingsystem.bankingsystem.repository.AccountRepository;
import com.bankingsystem.bankingsystem.repository.TransactionRepository;
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

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction makeTransaction(Long accountId, Transaction transaction) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setAccount(account.get());

            // Update the account balance based on transaction type
            if ("deposit".equalsIgnoreCase(transaction.getTransactionType())) {
                account.get().setBalance(account.get().getBalance() + transaction.getAmount());
            } else if ("withdraw".equalsIgnoreCase(transaction.getTransactionType())) {
                if (account.get().getBalance() >= transaction.getAmount()) {
                    account.get().setBalance(account.get().getBalance() - transaction.getAmount());
                } else {
                    throw new RuntimeException("Insufficient balance");
                }
            }

            accountRepository.save(account.get());
            return transactionRepository.save(transaction);
        }
        return null;
    }
}
