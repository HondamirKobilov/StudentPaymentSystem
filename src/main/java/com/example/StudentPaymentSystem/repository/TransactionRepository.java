package com.example.StudentPaymentSystem.repository;

import com.example.StudentPaymentSystem.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByStudentId(Long studentId);

    Page<Transaction> findByDateBetweenAndAmountGreaterThanEqual(LocalDate from, LocalDate to, BigDecimal amount, Pageable pageable);
    boolean existsByStudentIdAndAmountAndDateAndNote(Long studentId, BigDecimal amount, LocalDate date, String note);

    List<Transaction> findByDateBetween(LocalDate from, LocalDate to);
}


