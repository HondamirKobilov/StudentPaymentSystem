package com.example.StudentPaymentSystem.dto;

import java.math.BigDecimal;

public class ReportDto {
    private BigDecimal totalAmount;
    private long transactionCount;

    public ReportDto(BigDecimal totalAmount, long transactionCount) {
        this.totalAmount = totalAmount;
        this.transactionCount = transactionCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(long transactionCount) {
        this.transactionCount = transactionCount;
    }
}
