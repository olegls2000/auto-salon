package com.bta.java.autosalon.controller;

import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.repository.CarRepository;
import com.bta.java.autosalon.service.SellService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {
  @Autowired
  private CarRepository carRepository;

  @Autowired
  private SellService sellService;

  @GetMapping("/available-cars")
  public ModelAndView getAllCars() {
    Map<String, Object> model = new HashMap<>();
    List<Car> availableCars = carRepository.findAll();
    model.put("cars", availableCars);
    ModelAndView result = new ModelAndView("car/available-cars", model);

    return result;
  }

  @GetMapping("/car/sell")
  public String sellCarById(@RequestParam Long id) {
    Car carToSell = carRepository.findById(id).get();
    sellService.sellCar(carToSell);
    return "index";
  }
}
