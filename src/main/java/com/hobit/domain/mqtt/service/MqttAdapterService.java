/*
package com.hobit.domain.mqtt.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class MqttAdapterService {


    @Value("${mqtt.broker-url}")
    private String BROKER_URL;

    @Value("${mqtt.BROKER_CLIENT_ID}")
    private String BROKER_CLIENT_ID;
    public String topic = "test";

    @Bean
    @Scope("prototype")
    public MessageProducer inboundChannel(
            MqttPahoClientFactory mqttClientFactory,
            MessageChannel mqttInputChannel
    ) { // inboundChannel 어댑터

        var adapter = new MqttPahoMessageDrivenChannelAdapter(
                BROKER_URL,
                BROKER_CLIENT_ID,
                mqttClientFactory,
                topic
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
}
*/
