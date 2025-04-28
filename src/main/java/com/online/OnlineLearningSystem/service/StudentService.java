package com.online.OnlineLearningSystem.service;

import com.online.OnlineLearningSystem.Model.Admin;
import com.online.OnlineLearningSystem.Model.Student;
import com.online.OnlineLearningSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
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

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public boolean updateStudent(long id, Student student) {
        if(studentRepository.existsById(id)){
            student.setId(id);
            return true;
        }
        return false;
    }
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
