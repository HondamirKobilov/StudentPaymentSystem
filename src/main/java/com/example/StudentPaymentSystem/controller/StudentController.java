package com.example.StudentPaymentSystem.controller;
import com.example.StudentPaymentSystem.model.Student;
import com.example.StudentPaymentSystem.service.StudentService;
import com.example.StudentPaymentSystem.util.PhoneValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student) {
        if (!PhoneValidator.isValid(student.getPhone())) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"message\": \"Telefon raqam noto‘g‘ri formatda kiritilgan.\"}");
        }

        Student saved = studentService.createStudent(student);
        if (saved == null) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"message\": \"Bu telefon raqam bilan foydalanuvchi allaqachon mavjud.\"}");
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        Page<Student> students = studentService.findAll(PageRequest.of(page, size));
        if (students.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("{\"message\": \"Studentlar topilmadi.\"}");
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return ResponseEntity
                    .status(404)
                    .body("{\"message\": \"Bunday IDga ega student topilmadi.\"}");
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.update(id, student);
        if (updated == null) {
            return ResponseEntity
                    .status(404)
                    .body("{\"message\": \"Bunday IDga ega student topilmadi yoki telefon raqam band.\"}");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = studentService.delete(id);
        if (!deleted) {
            return ResponseEntity
                    .status(404)
                    .body("{\"message\": \"Bunday IDga ega student topilmadi.\"}");
        }
        return ResponseEntity.ok("{\"message\": \"Student muvaffaqiyatli o‘chirildi.\"}");
    }

}
