package com.hobit.domain.sensor.entity;


import com.hobit.domain.sensor.dto.request.SensorSaveRequest;
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
    private String sensorValue;
    private String sensorType;
    @Builder
    public Sensor(String id,String sensorName,String sensorLocation,String sensorValue,String sensorType){
        this.id =id;
        this.sensorName=sensorName;
        this.sensorType=sensorType;
        this.sensorValue=sensorValue;
        this.sensorLocation=sensorLocation;
    }

    public void update(SensorSaveRequest request){
        this.sensorLocation= request.sensorLocation();
        this.sensorName = request.sensorName();
    }

}
