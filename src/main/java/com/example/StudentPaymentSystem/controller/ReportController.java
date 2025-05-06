package com.example.StudentPaymentSystem.controller;
import com.example.StudentPaymentSystem.dto.ReportDto;
import com.example.StudentPaymentSystem.model.Transaction;
import com.example.StudentPaymentSystem.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        ReportDto report = reportService.getSummary(from, to);

        if (report.getTotalAmount().compareTo(BigDecimal.ZERO) == 0 && report.getTransactionCount() == 0) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Hisobot topilmadi.");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(report);
    }

    @GetMapping("/students/{studentId}/transactions")
    public ResponseEntity<?> getStudentTransactions(@PathVariable Long studentId) {
        List<Transaction> transactions = reportService.getStudentTransactions(studentId);

        if (transactions.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Talabaga oid tranzaksiyalar topilmadi.");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(transactions);
    }
}
