package com.example.StudentPaymentSystem.service;
import com.example.StudentPaymentSystem.model.Student;
import com.example.StudentPaymentSystem.model.Transaction;
import com.example.StudentPaymentSystem.repository.StudentRepository;
import com.example.StudentPaymentSystem.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final StudentRepository studentRepository;

    public TransactionService(TransactionRepository transactionRepository, StudentRepository studentRepository) {
        this.transactionRepository = transactionRepository;
        this.studentRepository = studentRepository;
    }

    public Transaction createTransaction(Long studentId, Transaction transaction) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student topilmadi"));

        boolean exists = transactionRepository.existsByStudentIdAndAmountAndDateAndNote(
                studentId,
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getNote()
        );

        if (exists) {
            throw new IllegalArgumentException("Bu tranzaksiya avval qo‘shilgan.");
        }

        transaction.setStudent(student);
        return transactionRepository.save(transaction);
    }


    public Page<Transaction> filterTransactions(LocalDate from, LocalDate to, BigDecimal minAmount, Pageable pageable) {
        return transactionRepository.findByDateBetweenAndAmountGreaterThanEqual(from, to, minAmount, pageable);
    }


    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction update(Long id, Transaction updatedTransaction) {
        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tranzaksiya topilmadi"));
        existing.setAmount(updatedTransaction.getAmount());
        existing.setDate(updatedTransaction.getDate());
        existing.setNote(updatedTransaction.getNote());
        return transactionRepository.save(existing);
    }

    public boolean delete(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
