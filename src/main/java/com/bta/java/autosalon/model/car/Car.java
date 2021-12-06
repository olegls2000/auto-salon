package com.bta.java.autosalon.model.car;

import com.bta.java.autosalon.model.Balance;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "car")

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Car {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
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

  @OneToMany(mappedBy = "car")
  private List<Balance> transactions;
}
