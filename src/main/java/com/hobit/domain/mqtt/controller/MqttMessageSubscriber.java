package com.hobit.mqtt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Slf4j
public class MqttMessageSubscriber implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info(message.toString());
        System.out.println("messge test2 ");
        System.out.println(message);
    }


}
