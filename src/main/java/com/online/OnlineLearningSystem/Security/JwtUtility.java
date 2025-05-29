package com.online.OnlineLearningSystem.Security;

import com.online.OnlineLearningSystem.Model.Role;
import com.online.OnlineLearningSystem.Model.UserLog;
import com.online.OnlineLearningSystem.repository.StudentRepository;
import com.online.OnlineLearningSystem.repository.UserLogRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtUtility {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);
    //secret key
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //expiration time
    private final  long jwtExpirationMilliSecond = 24 * 60 * 60 * 1000L; ;

    private final UserLogRepository userLogRepository;

    private StudentRepository studentRepository;

    public JwtUtility(StudentRepository studentRepository, UserLogRepository userLogRepository) {
        this.studentRepository = studentRepository;
        this.userLogRepository = userLogRepository;
    }

    //generate JWT token
    public String generateToken(String username) {
        Optional<UserLog> userOpt = userLogRepository.findByUserName(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Set<Role> roles = userOpt.get().getRoles();
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("User has no roles assigned.");
        }

        // Example: take the first role's name
        String roleName = roles.iterator().next().getName();

        return Jwts.builder()
                .setSubject(username)
                .claim("role", roleName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMilliSecond))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }


    //extract username
    public static String extractUserName(String token) {
        return parseClaims(token).getSubject();
    }

    //extract Role
    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    //JWT token validation
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.warn("Invalid JWT Token: {}", e.getMessage());
            return false;
        }
    }

    //parse JWT claims
    private static Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
