package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {

        System.out.println("Notificação enviada para o usuário!");
    }
//        String email = user.getEmail();
//        NotificationDTO request = new NotificationDTO(email, message);

//        ResponseEntity<Void> response = restTemplate.postForEntity(
//                "https://util.devi.tools/api/v1/notify",
//                request,
//                Void.class // não esperamos body mesmo
//        );
//
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new Exception("Falha ao enviar notificação. Código: " + response.getStatusCode());
//        }
//
//        System.out.println("Notificação enviada com sucesso. Status: " + response.getStatusCode());
//    }
}
