package com.picpaysimplificado.services;


import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.LoginDTO;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void login(LoginDTO loginDTO) throws Exception {
        //achar o usuário

        User user = userRepository.findByEmail(loginDTO.email()).orElseThrow(()-> new Exception("Usuário não encontrado!"));

        //validar a senha

        boolean passwordMatches = passwordEncoder.matches(loginDTO.password(), user.getPassword());

        if(!passwordMatches) {
            throw new Exception("Senha inválida");
        } else {
            System.out.println("Usuário autenticado");
        }
    }
}
