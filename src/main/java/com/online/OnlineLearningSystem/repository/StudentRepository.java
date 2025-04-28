package com.online.OnlineLearningSystem.repository;

import com.online.OnlineLearningSystem.Model.Course;
import com.online.OnlineLearningSystem.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

