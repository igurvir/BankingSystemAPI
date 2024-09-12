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
    Optional<User> user = userRepository.findById(userId);
    Optional<AccountType> accountType = accountTypeRepository.findById(accountTypeId);

    if (user.isPresent() && accountType.isPresent()) {
        account.setUser(user.get());
        account.setAccountType(accountType.get()); // Make sure this line is setting the account type correctly
        return accountRepository.save(account);
    }
    return null;
}


    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
