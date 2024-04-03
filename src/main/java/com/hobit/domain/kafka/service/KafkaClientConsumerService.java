package com.hobit.domain.kafka.service;

import com.hobit.domain.kafka.entity.KafkaClientConsumer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class KafkaClientConsumerService {



    private static final ConcurrentHashMap<String, KafkaClientConsumer> consumers = new ConcurrentHashMap<>();


    public static KafkaClientConsumer createKafkaConsumer(String topic){
        KafkaClientConsumer consumer=new KafkaClientConsumer(topic);
        consumer.start();
        consumers.put(topic,consumer);
        return consumer;
    }

}
