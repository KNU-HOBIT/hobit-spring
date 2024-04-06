package com.hobit.domain.kafka.entity;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
public class KafkaClientConsumer implements Runnable {

    private String BROKER_URL = "tcp://155.230.34.51:30083";
    private final String topic;
    private KafkaConsumer kafkaConsumer;

    public KafkaClientConsumer(String topic) {
        this.topic = topic;
    }
    public void start() {
        new Thread(this).start();  // Ensures that each consumer instance runs in its own thread
    }
    public Thread getRunningThreads(String topic) {
        List<Thread> allThreads = (List<Thread>) Thread.getAllStackTraces().keySet();
        Thread topicThreads = new Thread();

        for (Thread thread : allThreads) {
            if (thread.getName().contains(topic) && thread.isAlive()) {
                topicThreads=thread;
                break;
            }
        }
        return topicThreads;
    }

    @Override
    public void run() {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_URL);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,topic);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);

        /**
         Kafka는 메시지를 바이트 배열 형태로 전송합니다. 이는 메시지가 다양한 프로그래밍 언어와 시스템에서 전송 및 소비될 수 있도록 하기 위해서입니다.
                하지만 대부분의 애플리케이션은 메시지를 직접 바이트 배열로 처리하지 않습니다. 대신, 메시지를 의미 있는 객체로 변환하여 사용해야 합니다.
        직렬화는 메시지를 객체에서 바이트 배열로 변환하는 과정입니다. 역직렬화는 바이트 배열을 다시 객체로 변환하는 과정입니다.
                Kafka 소비자는 메시지를 받을 때 역직렬화를 사용하여 바이트 배열을 사용 가능한 객체로 변환해야 합니다.

         Kafka에서 직렬화와 역직렬화는 다음과 같이 수행됩니다.
         프로듀서: 프로듀서는 메시지를 직렬화하기 전에 Serializer 클래스를 사용합니다. Kafka는 다양한 기본 제공 Serializer를 제공하며, 사용자 정의 Serializer를 만들 수도 있습니다.
         브로커: 브로커는 메시지를 저장할 때 직렬화된 바이트 배열을 사용합니다.
         소비자: 소비자는 메시지를 역직렬화하기 전에 Deserializer 클래스를 사용합니다. Kafka는 다양한 기본 제공 Deserializer를 제공하며, 사용자 정의 Deserializer를 만들 수도 있습니다.
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        **/

        /**
         * 일반적으로 Kafka는 SASL 또는 다른 메커니즘을 통해 인증하므로 사용자 이름 및 비밀번호 필드를 제거할 수 있습니다 (이 기본 예에서는 다루지 않음)
         *
         * SASL 인증
         */
        //properties.put(SaslConfigs.SASL_MECHANISM,"PLAIN");


        KafkaConsumer<String,String> consumer =new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));

        /**
         *  poll 메서드의 반환 결과는  ConsumerRecords<String, String> 객체입니다.
         *
         * Kafka 소비자가 poll(Duration timeout) 메서드를 호출하면, 이 메서드는 지정된 시간 동안 구독된 토픽에서 메시지를 기다립니다.
         * 하나의 메시지가 아니라 여러 개의 메시지 를 받을 수 있기 때문에 반환 타입은  ConsumerRecords 입니다.
         *
         * ConsumerRecords 객체는 다음과 같은 정보를 포함하는 개별 ConsumerRecord 객체들의 컬렉션입니다.
         *
         * 키 (key): 메시지의 키 (선택사항)
         * 값 (value): 메시지의 실제 데이터
         * 토픽 (topic): 메시지가 속한 토픽 이름
         * 파티션 (partition): 메시지가 속한 파티션 번호 (Kafka는 토픽을 여러 파티션으로 나눌 수 있음)
         * 오프셋 (offset): 파티션 내부에서 메시지의 순서를 나타내는 값
         * 코드에서 ConsumerRecords 를 사용하는 이유:
         *
         * 여러 메시지 처리: poll 메서드는 실제로 여러 개의 메시지를 받을 수 있으므로 ConsumerRecords 를 사용하면 하나의 루프 를 통해 모든 메시지를 처리 할 수 있습니다.
         * 메시지 정보 활용: ConsumerRecords 객체를 통해 각 메시지의 키, 토픽, 파티션, 오프셋 등의 정보에 접근할 수 있습니다. 이 정보는 특정 메시지 처리 로직에 필요할 수 있습니다.
         */
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String,String> record: records){
                String message = record.value();
                String threadName = Thread.currentThread().getName();
                System.out.println("Message arrived. Topic: " + topic + ", Message: " + message + ", Thread: " + threadName);
            }
        }
    }
}
