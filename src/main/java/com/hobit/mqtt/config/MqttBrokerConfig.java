package com.hobit.mqtt.config;

import com.hobit.mqtt.controller.MqttMessageSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@Slf4j
public class MqttBrokerConfig {
    private static final String BROKER_URL = "tcp://155.230.34.51:30083";
    private static final String BROKER_CLIENT_ID = "unique-client-id";
    private static final String TOPIC_FILTER = "my-topic";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    @Bean
    public MqttPahoClientFactory mqttClientFactory() { // MQTT 클라이언트 관련 설정
        var factory = new DefaultMqttPahoClientFactory();
        var options = new MqttConnectOptions();
        options.setServerURIs(new String[]{BROKER_URL});
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());
        options.setAutomaticReconnect(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageProducer inboundChannel(
            MqttPahoClientFactory mqttClientFactory,
            MessageChannel mqttInputChannel
    ) { // inboundChannel 어댑터

        var adapter = new MqttPahoMessageDrivenChannelAdapter(
                BROKER_URL,
                BROKER_CLIENT_ID,
                mqttClientFactory,
                TOPIC_FILTER
        );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel);
        return adapter;
    }

    @Bean
    public MessageChannel mqttInputChannel() { // MQTT 구독 채널 생성
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel") // MQTT 구독 핸들러
    public MessageHandler inboundMessageHandler() {
        return new MqttMessageSubscriber();
    }
}