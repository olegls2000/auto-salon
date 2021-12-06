package com.bta.java.autosalon.repository;

import com.bta.java.autosalon.model.Balance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

  @Query("select bl from com.bta.java.autosalon.model.Balance bl order by transaction_time desc")
  List<Balance> findLatestTransaction();
}
