/*
package com.hobit.domain.mqtt.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MqttManagementService {

    private final MqttClientConsumerFactory mqttClientConsumerFactory;


    @PostConstruct
    @Bean
    public void initializeConsumers(String topic) {
        // Example broker URL and topic - these could be dynamically determined at runtime

        // Create and start MQTTConsumer instances dynamically
        MqttClientConsumerFactory.createConsumer(topic);

        // You can extend this method to manage these instances further (e.g., stop, restart)
    }




}
*/
