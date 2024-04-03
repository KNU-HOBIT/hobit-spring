/*
package com.hobit.domain.mqtt.controller;

import com.hobit.domain.mqtt.dto.request.MqttConnectionRequest;

import com.hobit.domain.mqtt.service.MqttClientConsumerService;
import com.hobit.domain.mqtt.entity.MqttConsumer;
import com.hobit.global.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mqtt")
@Slf4j
public class MqttDynamicController {


    private final MqttClientConsumerService mqttClientFactory;


    @PostMapping
    public ResponseEntity<ApiUtil.ApiSuccessResult<String>> saveBean(
            @RequestBody MqttConnectionRequest mqttConnectionRequest
    ) {
        String topic = mqttConnectionRequest.MqttTopic();

        // client : 인스턴스 생성 및 새로운 쓰레드 생성 및 start -> run
        MqttConsumer mqttConsumer = mqttClientFactory.createConsumer(topic);

        // client log 확인
        log.info("인스턴스 생성 완료: 빈 이름 - {}, 클래스 - {}", topic, mqttClientFactory.getClass().getName());
        log.info("인스턴스 등록 테스트 {} , topic {}", mqttClientFactory, topic);


        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.CREATED));
    }
}
*/
