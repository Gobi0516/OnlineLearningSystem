package com.online.OnlineLearningSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true , nullable=false)
    private String UserName;

    @Column(nullable = false)
    private String Password;

    @Column(unique = true, nullable = false)
    private String Email;

//    @ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles=<>new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Set<Role> roles = new HashSet<>();

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
