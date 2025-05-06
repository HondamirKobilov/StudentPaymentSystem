package com.example.StudentPaymentSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {
    public BigDecimal amount;
    public LocalDate date;
    public String note;
}
