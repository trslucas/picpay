package com.picpaysimplificado.config.aws;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Service
public class AwsSnsConfig {

   @Value("${aws.accessKeyId}")
    private String accessKeyId;

   @Value("${aws.secret.access.key}")
    private String secretKey;

   @Value("${aws.region}")
    private String region;

   @Value("${aws.sns.topic.arn}")
    private String topicArn;


   @Bean
    public SnsClient snsClientBuilder() {
       return SnsClient.builder()
               .region(Region.of(region))
               .credentialsProvider(StaticCredentialsProvider
                       .create(AwsBasicCredentials.create(accessKeyId, secretKey))
               )
               .build();
   }

   @Bean(name = "transactionsTopicArn")
    public String transactionsTopicArnBuider() {
       return topicArn;
   }

}
