package com.example.AttaSystem.controllers;


import com.example.AttaSystem.entities.Place;
import com.example.AttaSystem.services.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/place")
@Slf4j
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping(value = "/add/name/{name}")
    public ResponseEntity<Place> addPlace(@PathVariable("name") String name) {
        Place place = placeService.addNewPlace(name);
        return new ResponseEntity<>(place, HttpStatus.CREATED);
    }

}
