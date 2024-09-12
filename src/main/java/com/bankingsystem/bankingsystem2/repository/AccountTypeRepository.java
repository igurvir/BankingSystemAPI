package com.bankingsystem.bankingsystem2.repository;

import com.bankingsystem.bankingsystem2.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
