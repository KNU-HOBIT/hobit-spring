package com.hobit.domain.mqtt.service;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.stereotype.Service;

@Service

public class MqttClientService {
    private String identifier;

    @Value("${mqtt.broker-url}")
    private String BROKER_URL;

    @Value("${mqtt.username}")
    private String USERNAME ;

    @Value("${mqtt.password}")

    private String PASSWORD;

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    @Bean
    @Scope("prototype")
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
}
