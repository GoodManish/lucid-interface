package com.example.config.producer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
 
@Configuration
public class KafkaProducerConfig 
{
//  @Value(value = "${kafka.bootstrapAddress}")
//    private String bootstrapAddress;
   
  //1. Send string to Kafka
   
  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9099"); //Lucid kafka host
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(props);
  }
    
  @Bean(name = "kafkaLucidTemplate")
  public KafkaTemplate<String, String> kafkaLucidTemplate() {
      return new KafkaTemplate<>(producerFactory());
  }


  //TODO: To produce messages to Transact Kafka topic (transact-update-table), so that it can imitate DES flow.
  @Bean
  public ProducerFactory<String, String> producerTransactFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //Transact kafka host
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(props);
  }

  @Bean(name = "kafkaTransactTemplate")
  public KafkaTemplate<String, String> kafkaTransactTemplate() {
    return new KafkaTemplate<>(producerTransactFactory());
  }
   
//  //2. Send User objects to Kafka
//  @Bean
//    public ProducerFactory<String, User> userProducerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, User> userKafkaTemplate() {
//        return new KafkaTemplate<>(userProducerFactory());
//    }
}