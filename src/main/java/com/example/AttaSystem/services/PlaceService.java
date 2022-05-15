package com.example.AttaSystem.services;

import com.example.AttaSystem.entities.Place;
import com.example.AttaSystem.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public Place addNewPlace(String name){
        return placeRepository.save(new Place(name));
    }
}
