package com.picpaysimplificado.controllers;


import com.picpaysimplificado.dtos.LoginDTO;
import com.picpaysimplificado.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO data) {
        try {
            authService.login(data);
            return ResponseEntity.ok("Login Bem sucedido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
