package com.bta.java.autosalon.model;

import com.bta.java.autosalon.model.car.Car;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Place {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private Integer number;

  @OneToOne
  @JoinColumn(name = "car_id")
  private Car car;
}
