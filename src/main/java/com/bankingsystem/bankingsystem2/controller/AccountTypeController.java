package com.bankingsystem.bankingsystem2.controller;

import com.bankingsystem.bankingsystem2.model.AccountType;
import com.bankingsystem.bankingsystem2.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account-types")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @GetMapping
    public List<AccountType> getAllAccountTypes() {
        return accountTypeService.getAllAccountTypes();
    }
}
