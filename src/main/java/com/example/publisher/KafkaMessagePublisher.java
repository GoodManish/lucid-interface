package com.example.publisher;

import com.example.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {
    private final KafkaTemplate<String, Object> kafkaTransactTemplate;

    public KafkaMessagePublisher(@Qualifier("kafkaTransactTemplate") KafkaTemplate<String, Object> kafkaTransactTemplate) {
        this.kafkaTransactTemplate = kafkaTransactTemplate;
    }
    public void sendStringMessageToTransactTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = kafkaTransactTemplate.send("transact-update-table", message);
        future.whenComplete((result, ex) ->{
            if(ex==null) log.info("Sent message=[" + message +"] with offset=[" + result.getRecordMetadata().offset() + "]");
            else log.error("Unable to send message=[" + message + "] due to : " +ex.getMessage());
        });
    }
    public void sendUserToTransactTopic(User user){
        CompletableFuture<SendResult<String, Object>> future = kafkaTransactTemplate.send("transact-update-table", user);
        future.whenComplete((result, ex) ->{
            if(ex==null) log.info("Sent user=[" + user +"] with offset=[" + result.getRecordMetadata().offset() + "]");
            else log.error("Unable to send user=[" + user + "] due to : " +ex.getMessage());
        });
    }


}
