package com.bankingsystem.bankingsystem2.repository;

import com.bankingsystem.bankingsystem2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Custom query to find all transactions by account ID
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
    List<Transaction> findByAccountId(@Param("accountId") Long accountId);
}
