package com.bankingsystem.bankingsystem.service;

import com.bankingsystem.bankingsystem.model.Account;
import com.bankingsystem.bankingsystem.model.User;
import com.bankingsystem.bankingsystem.repository.AccountRepository;
import com.bankingsystem.bankingsystem.repository.UserRepository;
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

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Long userId, Account account) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            account.setUser(user.get());
            return accountRepository.save(account);
        }
        return null;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
