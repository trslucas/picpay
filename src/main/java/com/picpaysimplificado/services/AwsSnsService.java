package com.picpaysimplificado.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpaysimplificado.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;


@Service
public class AwsSnsService {

    private final SnsClient snsClient;
    private final String transactionsTopic;
    private final ObjectMapper objectMapper;


    public AwsSnsService(SnsClient snsClient, @Qualifier("transactionsTopicArn") String transactionsTopic, ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.transactionsTopic = transactionsTopic;
        this.objectMapper = objectMapper;
    }

    public void publish(MessageDTO message) {

        try {
            String messageJson = objectMapper.writeValueAsString(message);
            PublishRequest request = PublishRequest.builder()
                    .topicArn(transactionsTopic)
                    .message(messageJson)
                    .build();

            snsClient.publish(request);
            System.out.println("Mensagem publicada: " + messageJson);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao publicar mensagem no SNS", e);
        }

    }
}
