package com.online.OnlineLearningSystem.repository;

import com.online.OnlineLearningSystem.Model.Course;
import com.online.OnlineLearningSystem.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}

