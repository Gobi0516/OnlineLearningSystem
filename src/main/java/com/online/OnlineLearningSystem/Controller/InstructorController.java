package com.online.OnlineLearningSystem.Controller;

import com.online.OnlineLearningSystem.Model.Instructor;
import com.online.OnlineLearningSystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

   
    private final InstructorService service;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.service = instructorService;
    }

    @GetMapping
    public List<Instructor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Instructor> getInstructorById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(service.create(instructor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> update(@PathVariable Long id, @RequestBody Instructor instructor) {
        Instructor updated = service.update(id, instructor);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> delete(@PathVariable Long id) {
        return service.delete(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }



}
