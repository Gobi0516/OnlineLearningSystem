package com.online.OnlineLearningSystem.DTO;

import com.online.OnlineLearningSystem.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Role role;
    private Long id;

}
