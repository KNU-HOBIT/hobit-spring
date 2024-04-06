package com.hobit.domain.kafka.controller;


import com.hobit.domain.kafka.dto.request.KafkaClientConsumerGetTopic;
import com.hobit.domain.kafka.entity.KafkaClientConsumer;
import com.hobit.domain.kafka.service.KafkaClientConsumerService;
import com.hobit.global.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
@Slf4j
public class KafkaDynamicController {

    private final KafkaClientConsumerService kafkaClientConsumerService;

    @PostMapping
    public ResponseEntity<ApiUtil.ApiSuccessResult<String>> saveKafka(
            @RequestBody KafkaClientConsumerGetTopic kafkaClientConsumerGetTopic
    ){
        String topic = kafkaClientConsumerGetTopic.KafkaTopic();
        // client : 인스턴스 생성 및 새로운 쓰레드 생성 및 start -> run
        KafkaClientConsumer kafkaClientConsumer =KafkaClientConsumerService.createKafkaConsumer(topic);

        // client log 확인
        log.info("인스턴스 등록 테스트 {} , topic {}", kafkaClientConsumer, topic);
        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.CREATED));
    }
    @DeleteMapping
    public ResponseEntity<ApiUtil.ApiSuccessResult<String>> deleteKafka(
            @RequestBody KafkaClientConsumerGetTopic kafkaClientConsumerGetTopic
    ){
        log.info("KafkaClientConsumerGetTopic : {}",kafkaClientConsumerGetTopic.KafkaTopic());
        KafkaClientConsumerService.deleteKafkaConsumer(kafkaClientConsumerGetTopic.KafkaTopic());
        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.OK));
    }

}
