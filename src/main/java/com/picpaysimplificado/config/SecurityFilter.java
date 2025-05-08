package com.picpaysimplificado.config;


import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

     String authHeader = request.getHeader("Authorization");

     if(authHeader == null || !authHeader.startsWith("Bearer ")) {
         filterChain.doFilter(request, response);
         return;
     }

     String token = authHeader.replace("Bearer ", "");
        String subject = null;
        try {
            subject = tokenService.getSubject(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = userRepository.findUserById(UUID.fromString(subject)).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));


     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());


     SecurityContextHolder.getContext().setAuthentication(authenticationToken);

     filterChain.doFilter(request, response);
    }

}
