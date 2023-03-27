package com.atrativos.back.repository;

import com.atrativos.back.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long>{

    @Query("SELECT c FROM City c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<City> findByName(String name);

    @Query("SELECT c FROM City c WHERE LOWER(c.state) LIKE LOWER(?1)")
    List<City> findAllCitiesByState(String state);

    @Query("SELECT c FROM City c WHERE c.name = ?1 AND c.state = ?2")
    Optional<City> existCity(String name, String state);

}
