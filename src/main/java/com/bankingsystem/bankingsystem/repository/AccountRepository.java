package com.bankingsystem.bankingsystem.repository;

import com.bankingsystem.bankingsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
