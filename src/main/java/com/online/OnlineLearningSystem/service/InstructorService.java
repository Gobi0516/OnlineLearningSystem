package com.online.OnlineLearningSystem.service;

import com.online.OnlineLearningSystem.Model.Instructor;
import com.online.OnlineLearningSystem.repository.InstructorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository repo;

    public InstructorService(InstructorRepository repo) {
        this.repo = repo;
    }

    public List<Instructor> getAll() {
        return repo.findAll();
    }

    public Optional<Instructor> getById(Long id) {
        return repo.findById(id);
    }

    public Instructor create(Instructor instructor) {
        return repo.save(instructor);
    }

    public Instructor update(Long id, Instructor instructor) {
        if (repo.existsById(id)) {
            instructor.setId(id);
            return repo.save(instructor);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }


}
