package com.bankingsystem.bankingsystem2.repository;

import com.bankingsystem.bankingsystem2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
