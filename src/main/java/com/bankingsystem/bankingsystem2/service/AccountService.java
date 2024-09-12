package com.bankingsystem.bankingsystem2.service;

import com.bankingsystem.bankingsystem2.model.Account;
import com.bankingsystem.bankingsystem2.model.User;
import com.bankingsystem.bankingsystem2.repository.AccountRepository;
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

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> createAccount(Long userId, Account account) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            account.setUser(user.get());
            return Optional.of(accountRepository.save(account));
        } else {
            return Optional.empty();
        }
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
