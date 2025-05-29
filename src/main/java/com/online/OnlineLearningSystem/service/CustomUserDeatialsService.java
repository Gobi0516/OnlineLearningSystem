package com.online.OnlineLearningSystem.service;

import com.online.OnlineLearningSystem.Model.UserLog;
import com.online.OnlineLearningSystem.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDeatialsService implements UserDetailsService {
   // @Autowired
    private final UserLogRepository userLogRepository;

    public CustomUserDeatialsService(UserLogRepository userLogRepository) {

        this.userLogRepository = userLogRepository;
    }


//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserLog userLog =userLogRepository.findByUserName().orElseThrow(()->new UsernameNotFoundException("User Not Fond"+username));
//        return new org.springframework.core.userDetails.UserLog(userLog.getUserName(),userLog.getPassword(),userLog.getRole().stream.map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLog user = userLogRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " has no assigned roles.");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );

    }
}
