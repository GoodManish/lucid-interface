package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafKaConsumerService {

  private final KafKaProducerService kafKaProducerService;

  public KafKaConsumerService(KafKaProducerService kafKaProducerService) {
    this.kafKaProducerService = kafKaProducerService;
  }

  @KafkaListener(topics = "transact-update-table", groupId = "transact-grp-1")
  public void consume(String message) {
    log.info("Message received from transact -> {}", message);
    try {
      this.kafKaProducerService.sendMessage(message);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("error occurred while sending message : {}", e.getMessage());
    }
  }

//  @KafkaListener(topics = "${user.topic.name}",
//      groupId = "${user.topic.group.id}",
//      containerFactory = "userKafkaListenerContainerFactory")
//  public void consume(User user) {
//    logger.info(String.format("User created -> %s", user));
//  }
}