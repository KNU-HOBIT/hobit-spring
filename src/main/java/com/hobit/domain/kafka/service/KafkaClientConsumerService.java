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

    public static String deleteKafkaConsumer(String topic){
        /**
         * ConcurrentHashMap의 remove() 메서드는 주어진 키에 대한 값을 맵에서 제거하고 반환함.
         * KafkaClientConsumer 인스턴스는 객체이기 때문에 참조로 반환됩니다.
         * 참조가 더 이상 존재하지 않는 객체는 가비지 컬렉션 대상이 됩니다.
         * 가비지 컬렉션은 더 이상 사용되지 않는 객체를 메모리에서 자동으로 삭제하는 메커니즘입니다.
         * consumers 맵에서 제거된 KafkaClientConsumer 인스턴스는 더 이상 맵에 의해 참조되지 않습니다.
         * 따라서 가비지 컬렉션이 실행될 때 자동으로 삭제됩니다.
         */
        KafkaClientConsumer consumer =consumers.remove(topic);


        /**
         * 삭제하려는 kafka 인스턴스가 사용중인 쓰레드를 반환함.
         */
        Thread backThread = consumer.getRunningThreads(topic);

        /**
         * 대부분의 경우 interrupt() 메서드만 호출하면 쓰레드 중단 요청을 보내는 데 충분합니다.
         * interrupt() 메서드는 쓰레드를 강제 종료시키지 않고 중단 요청만 전달합니다.
         */
        backThread.interrupt();


        return topic;
    }

}
