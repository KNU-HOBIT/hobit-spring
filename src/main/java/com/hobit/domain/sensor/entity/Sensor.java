package com.hobit.domain.sensor.entity;


import com.hobit.domain.sensor.dto.request.SensorSaveRequest;
import com.hobit.domain.sensor.dto.request.SensorUpdateRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensor")
@Data
public class Sensor {
    @Id
    private String id;
    private String sensorName;
    private String sensorLocation;
    private String sensorType;
    private String sensorTopic;
    @Builder
    public Sensor(String id,String sensorName,String sensorLocation,String sensorType,String sensorTopic){
        this.id =id;
        this.sensorName=sensorName;
        this.sensorType=sensorType;
        this.sensorTopic=sensorTopic;
        this.sensorLocation=sensorLocation;
    }

    public void update(SensorUpdateRequest request){
        this.sensorName = request.sensorName();
        this.sensorTopic = request.sensorTopic();
    }

}
