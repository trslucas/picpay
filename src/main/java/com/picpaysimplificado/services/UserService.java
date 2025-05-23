package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if(sender.getUserType() == UserType.MERCHANT) {

            throw new Exception("Usuário do tipo Lojista não está autorizado transação");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }


    public User findUserById(UUID id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(()-> new Exception("Usuário não encontrado"));
    }


    public User createUser(UserDTO data) {

        String encondedPassword = passwordEncoder.encode(data.password());



        User newUser = User.builder()
                .firstName(data.firstName())
                .lastName(data.lastName())
                .email(data.email())
                .password(encondedPassword)
                .balance(data.balance())
                .document(data.document())
                .userType(data.userType()).build();

        this.saveUser(newUser);

        return newUser;
    }


    public void saveUser(User user) {
        this.repository.save(user);
    }


    public List<User> getAllUsers() {
      return  this.repository.findAll();
    }
}
