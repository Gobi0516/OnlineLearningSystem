package com.online.OnlineLearningSystem.repository;

import com.online.OnlineLearningSystem.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
