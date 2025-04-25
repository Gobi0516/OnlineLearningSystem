package com.online.OnlineLearningSystem.service;

import com.online.OnlineLearningSystem.Model.Student;
import com.online.OnlineLearningSystem.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);

    }

    public Student update(Long id, Student student) {
        if (studentRepository.existsById(id)) {
             // student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
