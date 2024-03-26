package com.hobit.domain.sensor.service;

import com.hobit.domain.sensor.dto.request.SensorSaveRequest;
import com.hobit.domain.sensor.dto.response.SensorReadResponse;
import com.hobit.domain.sensor.entity.Sensor;
import com.hobit.domain.sensor.exception.SensorErrorCode;
import com.hobit.domain.sensor.repository.SensorRepository;
import com.hobit.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorService {


    private final SensorRepository sensorRepository;

    /**
     * CREATE
     */
    public String saveSensor(SensorSaveRequest request){
        Sensor sensor= Sensor.builder()
                .sensorName(request.sensorName())
                .sensorLocation(request.sensorLocation())
                .build();

        log.info("save Sensor name {}",sensor.getSensorName());
        sensorRepository.save(sensor);

        return sensor.getSensorName();
    }

    /**
     * READ
     */
    public SensorReadResponse sensorReadResponse(String id){
        Sensor sensor=sensorRepository.findById(id)
                .orElseThrow(()-> new GlobalException(SensorErrorCode.SENSOR_NOT_FOUND));

        return SensorReadResponse.toSensorDTO(sensor);
    }

    /**
     * UPDATE
     */
    public String updateSensor(SensorSaveRequest request,String id){
        Sensor sensor=sensorRepository.findById(id)
                .orElseThrow(()-> new GlobalException(SensorErrorCode.SENSOR_NOT_FOUND));
        sensor.update(request);
        sensorRepository.save(sensor);
        return sensor.getSensorName();
    }

    /**
     * DELETE
     */

    public void deleteSensor(String id){
        Sensor sensor=sensorRepository.findById(id)
                .orElseThrow(()-> new GlobalException(SensorErrorCode.SENSOR_NOT_FOUND));
        sensorRepository.delete(sensor);
    }

}
