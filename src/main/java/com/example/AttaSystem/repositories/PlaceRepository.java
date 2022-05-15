package com.example.AttaSystem.repositories;

import com.example.AttaSystem.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByName(String name);
}
