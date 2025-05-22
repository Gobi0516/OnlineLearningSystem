package com.online.OnlineLearningSystem.Controller;

import com.online.OnlineLearningSystem.Security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserLogController {

    @Autowired
    private JwtUtility jwtUtility;

    @Value("admin")
    private String roleAdmin;

    @Value("instructor")
    private String roleInstructor;

    @Value("student")
    private String roleStudent;

    @GetMapping("/protected-data")
    public ResponseEntity<String> getProtectedData(@RequestHeader("Authorization") java.lang.String token) {
        System.out.println(token);
        if (token != null && token.startsWith("Bearer "))//it have bearer prefix in jwt token
             {
            java.lang.String jwtToken = token.substring(7);//remove that bearer  prefix with substring
            try {
                if (jwtUtility.isTokenValid(jwtToken)) {
                    java.lang.String username = jwtUtility.extractUserName(jwtToken);//exract username from jwt

                    java.lang.String roles = jwtUtility.extractRole(jwtToken);

                    if (roles.equals(roleAdmin)) {
                        return ResponseEntity.ok("Welcome " + username + " Here is the " + roles + " - specific data");
                    } else if (roles.equals(roleStudent)) {
                        return ResponseEntity.ok("Welcome " + username + " Here is the " + roles + " - specific data");
                    } else if (roles.equals(roleInstructor)) {
                        return ResponseEntity.ok("Welcome " + username + " Here is the " + roles + " - specific data");
                    } else {
                        return ResponseEntity.status(403).body("Access Denied: You don't have the necessary role");

                    }
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Token");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing or invalid");

    }
}
