package com.bta.java.model.car;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Table(name = "CAR")
public class Car {

  @Id
  private Long id;

  private String color;

  @Enumerated(EnumType.STRING)
  @Column(name = "body_type")
  private BodyType bodyType;

  @Enumerated(EnumType.STRING)
  private Manufacturer manufacturer;

  @Enumerated(EnumType.STRING)
  @Column(name = "gear_type")
  private GearType gearType;

  @Enumerated(EnumType.STRING)
  @Column(name = "fuel_type")
  private FuelType fuelType;

  @Column(name = "release_date")
  private LocalDate releaseDate;

  private String description;
}
