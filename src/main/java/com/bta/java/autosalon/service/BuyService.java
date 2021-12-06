package com.bta.java.autosalon.service;

import com.bta.java.autosalon.model.Balance;
import com.bta.java.autosalon.model.Place;
import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.repository.BalanceRepository;
import com.bta.java.autosalon.repository.CarRepository;
import com.bta.java.autosalon.repository.PlaceRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyService {

  @Autowired
  private ParkingPlaceValidator parkingPlaceValidator;

  @Autowired
  private BalanceRepository balanceRepository;

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private PlaceRepository placeRepository;

  public boolean evaluatePrice(Car carToBuy, long clientPrice) {
    final long salonPrice = evaluateCar(carToBuy);
    final long priceDifference = (clientPrice - salonPrice) * 100 / salonPrice;

    return priceDifference <= 15;
  }

  public void buyCar(Car carToBuy, long price) {
    if (!parkingPlaceValidator.hasFreePlace()) {
      System.out.println("No free parking places");
      return;
    }
    long balance = balanceRepository.findAllOrderedByTransactionTime().stream()
        .findFirst().get().getBalance();
    if (price > balance) {
      System.out.println("No sufficient balance");
      return;
    }
    carRepository.save(carToBuy);
    parkCarAfterBuy(carToBuy);
    applyTx(carToBuy, balance, price);
  }

  private void parkCarAfterBuy(Car car) {
    final int freePlace = parkingPlaceValidator.getFreePlace();
    Place place = Place.builder()
        .number(freePlace)
        .car(car)
        .build();
    placeRepository.save(place);
  }

  private void applyTx(Car car, Long currentBalance, Long price) {
    long newBalance = currentBalance - price;
    Balance buyTx = Balance.builder()
        .summ(-price)
        .balance(newBalance)
        .car(car)
        .build();
    balanceRepository.save(buyTx);
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
