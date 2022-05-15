package com.example.AttaSystem.controllers;

import com.example.AttaSystem.services.SportService;
import com.example.AttaSystem.entities.Sport;
import com.example.AttaSystem.enums.Months;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/sport")
@Slf4j
public class SportController {

    @Autowired
    private  SportService sportService;

    @PostMapping(value = "/add/name/{name}/placeName/{placeName}")
    public ResponseEntity<Sport> addSport(@PathVariable("name") String name, @PathVariable("placeName") String placeName, @RequestParam ("start") Months start, @RequestParam ("end") Months end, @RequestParam ("price") Integer price) {
        Sport sport = sportService.saveNewSport(name, placeName,  start, end, price);
        return new ResponseEntity<>(sport, HttpStatus.CREATED);
    }

    @GetMapping(value = "/add/sportName/{sportName}/placeName/{placeName}")
    public ResponseEntity<Integer> addSport(@PathVariable("sportName") String sportName, @PathVariable("placeName") String placeName, @RequestParam ("startTime") Long startTime, @RequestParam ("endTime") Long endTime, @RequestParam ("locationName") String locationName) {
        return new ResponseEntity<>(sportService.bookSportAtPlace(sportName,  placeName,  locationName,  startTime,  endTime), HttpStatus.CREATED);
    }


}