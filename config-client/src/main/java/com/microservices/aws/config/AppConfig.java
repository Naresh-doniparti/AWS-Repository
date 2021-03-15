package com.microservices.aws.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "message")
public class AppConfig {

    @Value("${message}" )
    private String messageFromGit;

    @Bean
    @Qualifier("messageFromGit")
    public String getMessageFromGit(){
        return messageFromGit;
    }
}
