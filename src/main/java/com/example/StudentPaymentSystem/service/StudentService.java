package com.example.StudentPaymentSystem.service;
import com.example.StudentPaymentSystem.model.Student;
import com.example.StudentPaymentSystem.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student newStudent) {
        if (studentRepository.existsByPhone(newStudent.getPhone())) {
            return null;
        }
        return studentRepository.save(newStudent);
    }

    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public Student update(Long id, Student newStudent) {
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isEmpty()) {
            return null;
        }

        Student current = existing.get();
        if (!current.getPhone().equals(newStudent.getPhone())
                && studentRepository.existsByPhone(newStudent.getPhone())) {
            return null;
        }

        current.setFirstName(newStudent.getFirstName());
        current.setLastName(newStudent.getLastName());
        current.setPhone(newStudent.getPhone());
        current.setCourse(newStudent.getCourse());
        current.setGroupName(newStudent.getGroupName());
        current.setFaculty(newStudent.getFaculty());

        return studentRepository.save(current);
    }


    public boolean delete(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

}
