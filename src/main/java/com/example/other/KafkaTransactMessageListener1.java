package com.example.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
public class KafkaTransactMessageListener1 {
    Logger log = LoggerFactory.getLogger(KafkaTransactMessageListener1.class);

    @KafkaListener(topics = "transact-update-table", groupId = "transact-grp-1")
    public void consume1(String message){
        log.info("consumer1 consumed the message : {}" , message);
    }

/*  @KafkaListener(topics = "transact-update-table", groupId = "transact-grp-1")
    public void consume2(String message){
        log.info("consumer2 consumed the message : {}" , message);
    }
    @KafkaListener(topics = "transact-update-table", groupId = "transact-grp-1")
    public void consume3(String message){
        log.info("consumer3 consumed the message : {}" , message);
    }
    @KafkaListener(topics = "transact-update-table", groupId = "transact-grp-1")
    public void consume4(String message){
        log.info("consumer4 consumed the message : {}" , message);
    }
    */



}
