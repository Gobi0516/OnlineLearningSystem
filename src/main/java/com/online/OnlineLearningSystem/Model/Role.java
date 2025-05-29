package com.online.OnlineLearningSystem.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
