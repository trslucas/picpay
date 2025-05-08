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

    @Autowired
    private TokenService tokenService;
    public String login(LoginDTO loginDTO) throws Exception {

        User user = userRepository.findByEmail(loginDTO.email()).orElseThrow(()-> new Exception("Usuário não encontrado!"));

        boolean passwordMatches = passwordEncoder.matches(loginDTO.password(), user.getPassword());

        if(!passwordMatches) {
            throw new Exception("Senha inválida");
        }

            System.out.println("Usuário autenticado");

            return tokenService.generateToken(user);
    }
}
