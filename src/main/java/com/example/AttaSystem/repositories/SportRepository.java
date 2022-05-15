package com.example.AttaSystem.repositories;

import com.example.AttaSystem.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findFirstBySportName(String sportName);
}
