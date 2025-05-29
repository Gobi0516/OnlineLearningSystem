package com.online.OnlineLearningSystem.Security;

import com.online.OnlineLearningSystem.service.CustomUserDeatialsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    public JwtAuthenticationFilter(JwtUtility jwtUtility, CustomUserDeatialsService customUserDeatialsService) {
//
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String Token = request.getHeader("Authorization");

        if(Token != null && Token.startsWith("Bearer ")) {
            Token = Token.substring(7);
            String username = JwtUtility.extractUserName(Token);

//            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails=cutomUserDetailsService.loadUserByUsername(username);
//
//
//                   if(jwtUtility.isTokenValid(token)){
//                       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
//                       authenticationToken.SetDetails(new WebAu)
//                   }


        }

    }
}
