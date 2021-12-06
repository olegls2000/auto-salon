package com.bta.java.autosalon.service;

import com.bta.java.autosalon.model.car.Car;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;

@Service
public class BuyService {
  public void evaluatePrice(Car carToBuy, long clientPrice) {

  }

  public long evaluateCar(Car car) {
    long startPrice = 100_000;
    startPrice = evaluateByAge(startPrice, car);
    startPrice = evaluateByBodyType(startPrice, car);

    return startPrice;
  }

  private long evaluateByAge(long startPrice, Car car) {
    long carAge =
        ChronoUnit.YEARS.between(car.getReleaseDate(),
            LocalDateTime.now());
    if (carAge > 15) {
      startPrice *= 0.1;
    } else if (carAge > 10 && carAge <= 15) {
      startPrice *= 0.3;
    } else if (carAge > 5 && carAge <= 10) {
      startPrice *= 0.5;
    } else if (carAge > 0 && carAge <= 5) {
      startPrice *= 0.75;
    } else {
      startPrice *= 0.85;
    }
    return startPrice;
  }

  private long evaluateByBodyType(long startPrice, Car car) {
    switch (car.getBodyType()) {
      case SEDAN:
        System.out.println("Price is not changed!");
        break;
      case HATCH_BACK:
        System.out.println("Price is not changed!");
        break;
      case UNIVERSAL:
        startPrice += 500;
        break;
      case CABRIOLET:
        startPrice -= 1000;
        break;
      case CROSSOVER:
        startPrice += 200;
        break;
      case COUPE:
        startPrice *= 0.85;
        break;
      default:
        throw new RuntimeException("Unsupported Body Type!!!");

    }

    return startPrice;
  }

}
