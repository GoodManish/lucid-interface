package com.example.controller;

import com.example.dto.User;
import com.example.publisher.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 0; i <10; i++) {
                publisher.sendStringMessageToTransactTopic(message+" : " + i);
            }
            return ResponseEntity.ok("Message published successfully!!!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody User user) {
        try {
            publisher.sendUserToTransactTopic(user);
            return ResponseEntity.ok("User published successfully!!!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}
