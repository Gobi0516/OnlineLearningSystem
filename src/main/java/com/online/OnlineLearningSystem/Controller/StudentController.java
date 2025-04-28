package com.online.OnlineLearningSystem.Controller;

import com.online.OnlineLearningSystem.Model.Admin;
import com.online.OnlineLearningSystem.Model.Instructor;
import com.online.OnlineLearningSystem.Model.Student;
import com.online.OnlineLearningSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private  final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public List<Student> getAllStudents(@PathVariable int id) {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public Optional<Student> getStudentByID(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        boolean isUpdated = studentService.updateStudent(id, student);
//call services to update
        if (isUpdated) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.notFound().build(); // 404 if admin not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> delete(@PathVariable Long id) {
        return studentService.deleteStudent(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }
}
