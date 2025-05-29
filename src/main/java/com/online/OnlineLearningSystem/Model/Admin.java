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
    private Long id;
    private String adminName;
    private String password;//store hashed password
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdminName(String adminName) {
       this.adminName = adminName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
