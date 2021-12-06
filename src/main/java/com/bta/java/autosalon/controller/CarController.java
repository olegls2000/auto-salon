package com.bta.java.autosalon.controller;

import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.repository.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
  @Autowired
  private CarRepository carRepository;

  @RequestMapping(method = RequestMethod.GET, value = "/cars")
  public List<Car> getAllCars() {
    return carRepository.findAll();
  }
}
