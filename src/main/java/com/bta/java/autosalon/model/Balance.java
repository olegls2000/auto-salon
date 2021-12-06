package com.bta.java.autosalon.model;

import com.bta.java.autosalon.model.car.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(exclude = "car")
public class Balance {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(name = "transaction_time")
  private LocalDateTime transactionTime;

  private Long balance;

  private Long summ;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;
}
