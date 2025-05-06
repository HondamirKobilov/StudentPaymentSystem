package com.example.StudentPaymentSystem.repository;
import com.example.StudentPaymentSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByPhone(String phone);
}
