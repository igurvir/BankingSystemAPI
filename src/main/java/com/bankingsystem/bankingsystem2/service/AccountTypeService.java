package com.bankingsystem.bankingsystem2.service;

import com.bankingsystem.bankingsystem2.model.AccountType;
import com.bankingsystem.bankingsystem2.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }
}
