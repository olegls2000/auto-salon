package com.bta.java.autosalon.service;

import com.bta.java.autosalon.model.car.Car;

public interface BuyService {
  boolean evaluatePrice(Car carToBuy, long clientPrice);

  void buyCar(Car carToBuy, long price);

  long evaluateCar(Car car);
}
