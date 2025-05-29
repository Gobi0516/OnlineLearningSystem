package com.online.OnlineLearningSystem.Controller;


import com.online.OnlineLearningSystem.DTO.AuthResponse;
import com.online.OnlineLearningSystem.DTO.RegisterRequest;
import com.online.OnlineLearningSystem.Model.Role;
import com.online.OnlineLearningSystem.Model.UserLog;
import com.online.OnlineLearningSystem.Security.JwtUtility;
import com.online.OnlineLearningSystem.repository.RoleRepository;
import com.online.OnlineLearningSystem.repository.UserLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    public static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    //register user
    @Transactional
    @PostMapping("/regsiter")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        if (userLogRepository.findByUserName(registerRequest.getusername()).isPresent())
        {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userLogRepository.findByEmail(registerRequest.getEmail()))
        {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user account
        UserLog newUser = new UserLog();
        newUser.setUserName(registerRequest.getusername());
        newUser.setemail(registerRequest.getEmail());


        String encodedPassword=passwordEncoder.encode(registerRequest.getPassword());
        newUser.setPassword(encodedPassword);



        // Optional: Set default role
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));

        newUser.setRoles(Set.of(userRole));
        userLogRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }


    //login user
    @Transactional
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLog loginRequest){

        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );
        }
        catch (BadCredentialsException e)
        {
           //logger.
            return ResponseEntity.badRequest().body("Bad credentials!");
        }


        //Generate JWT
        String token = jwtUtility.generateToken(loginRequest.getUserName());
        return ResponseEntity.ok(token);



       // logger.info("Received login request for username: {}",loginRequest.getUserName());
//        //get user from db
//        UserLog userLog = userLogRepository.findByUserName(loginRequest.getUserName())
//                .orElseThrow(() -> new RuntimeException("Error: User not found."));
//
//        //create Response
//        AuthResponse authResponse = new AuthResponse(token, userLog.getRoles(), userLog.getId());
//
//
//        logger.info("User logged in successfully: {}", loginRequest.getUserName());
//        return ResponseEntity.ok("User logged in successfully!");

    }





    public PasswordEncoder passwordEncoder()
    {
        return passwordEncoder;
    }

    public JwtUtility jwtUtility()
    {
       return jwtUtility;
    }

    public RoleRepository roleRepository()
    {
        return roleRepository;
    }

    public UserLogRepository userLogRepository()
    {
        return userLogRepository;
    }

    public AuthenticationManager authenticationManager()
    {
        return authenticationManager;
    }
}
