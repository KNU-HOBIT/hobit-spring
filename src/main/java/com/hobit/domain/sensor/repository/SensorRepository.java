package com.hobit.domain.sensor.repository;

import com.hobit.domain.sensor.entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorRepository extends MongoRepository<Sensor,String> {
}
