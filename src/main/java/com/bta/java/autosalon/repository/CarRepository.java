package com.bta.java.autosalon.repository;

import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.model.car.FuelType;
import com.bta.java.autosalon.model.car.GearType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  List<Car> findAllByGearTypeAndFuelType(GearType gearType, FuelType fuelType);
}
