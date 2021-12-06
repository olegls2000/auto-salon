package com.bta.java.autosalon.service;

import com.bta.java.autosalon.model.Balance;
import com.bta.java.autosalon.model.Place;
import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.repository.BalanceRepository;
import com.bta.java.autosalon.repository.PlaceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellService {

  @Autowired
  private PlaceRepository placeRepository;

  @Autowired
  private BalanceRepository balanceRepository;

  public void sellCar(Car carToSell) {
    placeRepository.deleteByCar_Id(carToSell.getId());
    final long price = calculateSellPrice(carToSell);
    final Balance latestTx = balanceRepository.findLatestTransaction().stream().findFirst().get();
    Balance transaction = Balance.builder()
        .balance(price + latestTx.getBalance())
        .transactionTime(LocalDateTime.now())
        .summ(price)
        .car(carToSell)
        .build();
    balanceRepository.save(transaction);
  }

  private long calculateSellPrice(Car carToSell) {
    //TODO complete at home
    return 333l;
  }

}
