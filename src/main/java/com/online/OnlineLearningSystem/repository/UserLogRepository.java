package com.online.OnlineLearningSystem.repository;

import com.online.OnlineLearningSystem.Model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLogRepository extends JpaRepository<UserLog, Long>{
    Optional<UserLog> findByUserName(String userName);
    boolean findByEmail(String email);
}

