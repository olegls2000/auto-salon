package com.bta.java.autosalon.service;

import com.bta.java.autosalon.model.Balance;
import com.bta.java.autosalon.model.Place;
import com.bta.java.autosalon.model.car.Car;
import com.bta.java.autosalon.repository.BalanceRepository;
import com.bta.java.autosalon.repository.PlaceRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellService {

  private static final double SELL_INTEREST = 1.15;

  @Autowired
  private PlaceRepository placeRepository;

  @Autowired
  private BalanceRepository balanceRepository;

  @Transactional
  public void sellCar(Car carToSell) {
    placeRepository.deleteByCar_Id(carToSell.getId());
    final long price = calculateSellPrice(carToSell);
    final Balance latestTx =
        balanceRepository.findAllOrderedByTransactionTime().stream().findFirst().get();
    final Long newBalance = price + latestTx.getBalance();
    final Balance transaction = Balance.builder()
        .balance(newBalance)
        .transactionTime(LocalDateTime.now())
        .summ(price)
        .car(carToSell)
        .build();

    balanceRepository.save(transaction);
  }

  public long calculateSellPrice(Car carToSell) {
    final Balance buyTx = balanceRepository
        .findOneByCarIdAndSummLessThan(carToSell.getId(), 0L);
    final Long sellPrice = (long) (Math.abs(buyTx.getSumm()) * SELL_INTEREST);

    return sellPrice;
  }

}
