package com.atrativos.back.repository;

import com.atrativos.back.domain.Attractive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttractiveRepository extends JpaRepository<Attractive, Long>  {

    @Query("SELECT a FROM Attractive a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Attractive> findByName(String name);

    @Query("select a from Attractive a where LOWER(a.address) like LOWER(CONCAT('%', ?1, '%'))")
    List<Attractive> findByAddress(String address);

    @Query("select a from Attractive a where a.city.id = ?1")
    List<Attractive> findAllAttracivesByCity(Long id);
}
