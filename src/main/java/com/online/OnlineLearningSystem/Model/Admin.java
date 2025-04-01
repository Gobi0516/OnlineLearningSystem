package com.online.OnlineLearningSystem.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String AdminName;
    private String password;//store hashed password
    private String email;
}
