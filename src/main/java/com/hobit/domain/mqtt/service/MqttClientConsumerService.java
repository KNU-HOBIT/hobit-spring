package com.hobit.domain.mqtt.service;
import com.hobit.domain.mqtt.su.MqttConsumer;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MqttClientConsumerService {

    //MqttClientService 클래스는 싱글톤이지만, createConsumer 메서드는 싱글톤이 아님.
    //따라서 메서드를 호출할 때마다 새로운 MqttConsumer 인스턴스가 생성됩니다.

    private static final ConcurrentHashMap<String, MqttConsumer> consumers = new ConcurrentHashMap<>();
    // ConcurrentHashMap은 Java에서 제공하는 스레드 안전한 맵(HashMap) 구현체
    // 멀티 스레드 환경에서 여러 스레드가 동시에 맵에 데이터를 추가, 수정, 삭제할 수 있을 때 사용되고, 동시성 문제를 방지하기 위한 동기화 메커니즘 제공함.
    /**
     * create
     * @param topic
     * @return MqttConsumer
     */
    public static MqttConsumer createConsumer(String topic) {
        MqttConsumer consumer = new MqttConsumer(topic);
        consumer.start();
        // Use a unique identifier for each consumer, e.g., brokerUrl+topic
        consumers.put(topic, consumer);
        return consumer;
    }

    /**
     * READ
     */


    /**
     * Update
     */

    /**
     * Delete
     */



}
