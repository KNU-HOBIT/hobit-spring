package com.hobit.domain.mqtt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqttService implements BeanPostProcessor {


    private final MqttClientService mqttClientService;
    private final HashMap<String, MqttPahoClientFactory> MqttClient =new HashMap<>();


    /*
    BeanPostProcessor : 스프링이 빈 저장소에 등록할 목적으로 생성한 객체를 빈 저장소에 등록하기 직전에 조작하고 싶을때 이용
     */

    /**
     *
     * @param bean 새로운 bean으로 등록할 객체
     * @param beanName topic
     * @return 새롭게 생성된 Bean
     * @throws BeansException
     */
    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (beanName.equals("client")){
            MqttClient.put(beanName,(MqttPahoClientFactory) bean);
            log.info("bean test : {} ",bean);
        }
        else if( beanName.equals("adapter")){
            log.info("adapter");
        }else if(beanName.equals("handler")){
            log.info("handler");
        }
        else {
            log.info("not found");
        }

        return bean;
    }
}
