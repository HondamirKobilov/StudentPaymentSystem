package com.example.StudentPaymentSystem.service;

import com.example.StudentPaymentSystem.dto.ReportDto;
import com.example.StudentPaymentSystem.model.Transaction;
import com.example.StudentPaymentSystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;

    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ReportDto getSummary(LocalDate from, LocalDate to) {
        List<Transaction> transactions = transactionRepository.findByDateBetween(from, to);

        BigDecimal totalAmount = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long count = transactions.size();

        return new ReportDto(totalAmount, count);
    }

    public List<Transaction> getStudentTransactions(Long studentId) {
        return transactionRepository.findByStudentId(studentId);
    }
}
