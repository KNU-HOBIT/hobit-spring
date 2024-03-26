package com.hobit.domain.sensor.controller;

import com.hobit.domain.sensor.dto.request.SensorSaveRequest;
import com.hobit.domain.sensor.dto.response.SensorReadResponse;
import com.hobit.domain.sensor.entity.Sensor;
import com.hobit.domain.sensor.repository.SensorRepository;
import com.hobit.domain.sensor.service.SensorService;
import com.hobit.global.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;
    private final SensorService sensorService;


    /**
     *  sensor 등록
     * @param request sensor 이름, 위치
     * @return 저장한 sensor의 id 값
     */

    @PostMapping
    public ResponseEntity<ApiUtil.ApiSuccessResult<String>> saveSensor(
            @RequestBody SensorSaveRequest request
            ){
        String saveSensorId = sensorService.saveSensor(request);

        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.CREATED,saveSensorId));
    }

    /**
     *  sensor 조회
     * @param sensorId sensor의 id
     * @return sensor 정보
     */
    @GetMapping("/{sensorId}")
    public ResponseEntity<ApiUtil.ApiSuccessResult<SensorReadResponse>> readSensor(
            @PathVariable("sensorId") String sensorId
    ){
        SensorReadResponse response = sensorService.sensorReadResponse(sensorId);

        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.OK,response));
    }

    /**
     *  sensor 수정
     * @param sensorId sensor의 id
     * @param request 수정할 sensor 정보
     * @return 수정한 sensor의 id
     */
    @PutMapping("/{sensorId}")
    public ResponseEntity<ApiUtil.ApiSuccessResult<String>> updateSensor(
        @PathVariable("sensorId") String sensorId,
        @RequestBody SensorSaveRequest request
    ){
        String updateSensorId=sensorService.updateSensor(request,sensorId);

        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.OK,updateSensorId));
    }

    /**
     * sensor 삭제
     * @param sensorId 삭제할 sensor id
     * @return
     */


    @DeleteMapping("/{sensorId}")
    public ResponseEntity<ApiUtil.ApiSuccessResult<?>> deleteSensor(
            @PathVariable("sensorId") String sensorId
    ){
        sensorService.deleteSensor(sensorId);
        return ResponseEntity.ok().body(ApiUtil.success(HttpStatus.OK));
    }


}
