package com.bta.java.autosalon;

import com.bta.java.autosalon.model.car.BodyType;
import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.model.car.FuelType;
import com.bta.java.autosalon.model.car.GearType;
import com.bta.java.autosalon.model.car.Manufacturer;
import com.bta.java.autosalon.service.BuyService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bta.java.autosalon.repository.CarRepository;
import com.bta.java.autosalon.repository.BalanceRepository;
import com.bta.java.autosalon.repository.PlaceRepository;

@SpringBootApplication
public class AutoSalonApplication implements CommandLineRunner {

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private BalanceRepository balanceRepository;

  @Autowired
  private PlaceRepository placeRepository;

  @Autowired
  private BuyService buyService;

  public static void main(String[] args) {
    SpringApplication.run(AutoSalonApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Hello from Spring boot!!!");

    Car carToSell = Car.builder()
        .bodyType(BodyType.UNIVERSAL)
        .color("WHITE")
        .fuelType(FuelType.DIESEL)
        .gearType(GearType.AUTOMATIC)
        .manufacturer(Manufacturer.BMW)
        .releaseDate(LocalDate.of(2012, 2, 20))
        .description("Super good car!")
        .build();
    long myPrice = 25_000;
    if (buyService.evaluatePrice(carToSell, myPrice)) {
      buyService.buyCar(carToSell, myPrice);
    }

    System.out.println(carRepository.findAll());
    System.out.println(balanceRepository.findAll());
    System.out.println(placeRepository.findAll());
  }
}
