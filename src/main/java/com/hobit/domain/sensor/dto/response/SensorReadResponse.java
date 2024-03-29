package com.hobit.domain.sensor.dto.response;

import com.hobit.domain.sensor.entity.Sensor;
import lombok.Builder;

@Builder
public record SensorReadResponse(
        String sensorName,
        String sensorLoation,
        String sensorType,
        String sensorTopic
    ) {

    public static SensorReadResponse toSensorDTO(Sensor sensor){
        return SensorReadResponse.builder()
                .sensorName(sensor.getSensorName())
                .sensorLoation(sensor.getSensorLocation())
                .sensorType(sensor.getSensorType())
                .sensorTopic(sensor.getSensorTopic())
                .build();
    }
    }
