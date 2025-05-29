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
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

//    @ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles=<>new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "userlog_roles", // name of the join table
            joinColumns = @JoinColumn(name = "userlog_id"),      // FK to UserLog entity
            inverseJoinColumns = @JoinColumn(name = "role_id")    // FK to Role entity
    )
    private Set<Role> roles;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getemail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
