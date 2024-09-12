package com.bankingsystem.bankingsystem2.service;

import com.bankingsystem.bankingsystem2.model.Account;
import com.bankingsystem.bankingsystem2.model.AccountType;
import com.bankingsystem.bankingsystem2.model.User;
import com.bankingsystem.bankingsystem2.repository.AccountRepository;
import com.bankingsystem.bankingsystem2.repository.AccountTypeRepository;
import com.bankingsystem.bankingsystem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Long userId, Long accountTypeId, Account account) {
        // Fetch user by ID
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        // Fetch account type by ID
        Optional<AccountType> accountType = accountTypeRepository.findById(accountTypeId);
        if (!accountType.isPresent()) {
            throw new RuntimeException("Account type not found");
        }

        // Set the user and account type
        account.setUser(user.get());
        account.setAccountType(accountType.get());

        // Save and return the account
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
