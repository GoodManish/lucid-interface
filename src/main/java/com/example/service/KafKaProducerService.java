package com.example.service;

import com.example.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafKaProducerService {
    //1. General topic with a string payload

//    @Value(value = "${general.topic.name}")
//    private String topicName;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTemplate<String, User> userKafkaTemplate;
    public KafKaProducerService(@Qualifier("kafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate,
                                @Qualifier("userKafkaTemplate") KafkaTemplate<String, User> userKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.userKafkaTemplate = userKafkaTemplate;
    }
    //2. Topic with user object payload

//    @Value(value = "${user.topic.name}")
//    private String userTopicName;

//    @Autowired
//    private KafkaTemplate<String, User> userKafkaTemplate;
    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = this.kafkaTemplate.send("lucid-topic", message);
        future.whenComplete((result, ex) ->{
            if(ex==null){
                log.info("Sent message to lucid topic =[" + message +"] with offset=[" + result.getRecordMetadata().offset() + "]"+" ");
            }else {
                log.error("Unable to send message=[" + message + "] due to : " +ex.getMessage());
            }
        });
    }
    public void sendMessage(User user) {
        CompletableFuture<SendResult<String, User>> future = this.userKafkaTemplate.send("lucid-topic", user);
        future.whenComplete((result, ex) ->{
            if(ex==null){
                log.info("Sent message to lucid topic =[" + user +"] with offset=[" + result.getRecordMetadata().offset() + "]"+" ");
            }else {
                log.error("Unable to send message=[" + user + "] due to : " +ex.getMessage());
            }
        });
    }
//    public void saveCreateUserLog(User user) {
//        ListenableFuture<SendResult<String, User>> future = this.userKafkaTemplate.send(userTopicName, user);
//
//        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
//            @Override
//            public void onSuccess(SendResult<String, User> result) {
//                log.info("User created: "
//                        + user + " with offset: " + result.getRecordMetadata().offset());
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//                log.error("User created : " + user, ex);
//            }
//        });
//    }
}