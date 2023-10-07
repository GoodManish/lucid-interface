package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createTransactTopic(){
        return new NewTopic("transact-update-table", 3, (short) 1);
    }

    @Bean
    public NewTopic createLucidTopic(){
        return new NewTopic("lucid-topic", 3, (short) 1);
    }
}
