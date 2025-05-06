package com.example.StudentPaymentSystem.controller;
import com.example.StudentPaymentSystem.model.Transaction;
import com.example.StudentPaymentSystem.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/students/{studentId}/transactions")
    public ResponseEntity<?> create(@PathVariable Long studentId, @RequestBody Transaction transaction) {
        try {
            Transaction saved = transactionService.createTransaction(studentId, transaction);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getFilteredTransactions(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to,
            @RequestParam BigDecimal minAmount,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = transactionService.filterTransactions(from, to, minAmount, pageable);
        if (transactions.isEmpty()) {
            return ResponseEntity.status(404).body("{\"message\": \"Tranzaksiyalar topilmadi.\"}");
        }
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Transaction transaction = transactionService.getById(id);
        if (transaction == null) {
            return ResponseEntity.status(404).body("{\"message\": \"Tranzaksiya topilmadi.\"}");
        }
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Transaction transaction) {
        try {
            Transaction updated = transactionService.update(id, transaction);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"message\": \"Yangilashda xatolik: " + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = transactionService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("{\"message\": \"Tranzaksiya muvaffaqiyatli o‘chirildi.\"}");
        }
        return ResponseEntity.status(404).body("{\"message\": \"Tranzaksiya topilmadi.\"}");
    }
}
