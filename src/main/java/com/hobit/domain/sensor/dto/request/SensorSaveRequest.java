package com.hobit.domain.sensor.dto.request;

public record SensorSaveRequest(
        String sensorName,
        String sensorLocation
) {
}
