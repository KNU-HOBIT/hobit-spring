package com.hobit.domain.sensor.dto.request;

public record SensorUpdateRequest(
        String sensorName,
        String sensorTopic
) {
}
