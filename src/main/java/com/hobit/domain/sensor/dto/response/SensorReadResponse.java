package com.hobit.domain.sensor.dto.response;

import com.hobit.domain.sensor.entity.Sensor;
import lombok.Builder;

@Builder
public record SensorReadResponse(
        String sensorName,
        String sensorLoation
    ) {

    public static SensorReadResponse toSensorDTO(Sensor sensor){
        return SensorReadResponse.builder()
                .sensorName(sensor.getSensorName())
                .sensorLoation(sensor.getSensorLocation())
                .build();
    }
    }
