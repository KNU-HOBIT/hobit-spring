/*
package com.hobit.domain.mqtt.entity;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;


@Slf4j
public class MqttConsumer implements Runnable, MqttCallback {

    //@Value("${mqtt.broker-url}")
    private String BROKER_URL = "tcp://155.230.34.51:30083";

    //@Value("${mqtt.username}")
    private String USERNAME ="admin";

    //@Value("${mqtt.password}")
    private String PASSWORD="password123";
    private final String topic;
    private MqttClient mqttClient;


    // Constructor with dynamic configuration
    public MqttConsumer(String topic) {
        this.topic = topic;
    }
    public void start() {
        new Thread(this).start();  // Ensures that each consumer instance runs in its own thread
    }
    @Override
    public void run() {

        try {
            mqttClient = new MqttClient(BROKER_URL, MqttClient.generateClientId());
            mqttClient.setCallback(this);  // Set this class as the callback handler.MqttConsumer 클래스 자체를 mqttClient 인스턴스의 콜백 핸들러로 설정한다는 의미

            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            connectOptions.setUserName(USERNAME);
            connectOptions.setPassword(PASSWORD.toCharArray());

            log.info("Connecting to MQTT Broker at {}", BROKER_URL);
            mqttClient.connect(connectOptions); // Use the connect options with your credentials
            log.info("Connected successfully");
            mqttClient.subscribe(topic);  // Subscribe to the specified topic
            log.info("Subscribed to topic '{}'", topic);

        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.debug("Connection lost: " + throwable.getMessage());

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        // 현재 스레드의 이름을 얻습니다.
        String threadName = Thread.currentThread().getName();

        // 메시지 정보와 함께 현재 스레드의 이름을 출력합니다.
        System.out.println("Message arrived. Topic: " + topic + ", Message: " + new String(mqttMessage.getPayload()) + ", Thread: " + threadName);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
*/
