package com.hobit.domain.kafka.controller;


import com.hobit.domain.kafka.dto.request.KafkaClientConsumerSaveRequest;
import com.hobit.domain.kafka.entity.KafkaClientConsumer;
import com.hobit.domain.kafka.service.KafkaClientConsumerService;
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
@RequestMapping("/kafka")
@Slf4j
public class KafkaDynamicController {

    private final KafkaClientConsumerService kafkaClientConsumerService;

    @PostMapping
    public ResponseEntity<ApiUtil.ApiSuccessResult<String>> saveKafka(
            @RequestBody KafkaClientConsumerSaveRequest kafkaClientConsumerSaveRequest
    ){
        String topic = kafkaClientConsumerSaveRequest.KafkaTopic();
        // client : 인스턴스 생성 및 새로운 쓰레드 생성 및 start -> run
        KafkaClientConsumer kafkaClientConsumer =KafkaClientConsumerService.createKafkaConsumer(topic);

        // client log 확인
        log.info("인스턴스 등록 테스트 {} , topic {}", kafkaClientConsumer, topic);

        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.CREATED));

    }
}
