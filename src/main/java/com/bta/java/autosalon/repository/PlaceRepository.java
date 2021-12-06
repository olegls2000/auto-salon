package com.bta.java.autosalon.repository;

import com.bta.java.autosalon.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
  int deleteByCar_Id(Long id);
}
