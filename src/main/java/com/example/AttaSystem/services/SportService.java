package com.example.AttaSystem.services;

import com.example.AttaSystem.entities.Place;
import com.example.AttaSystem.entities.Sport;
import com.example.AttaSystem.enums.Months;
import com.example.AttaSystem.repositories.PlaceRepository;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.AttaSystem.repositories.SportRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SportService {


    @Autowired
    private  SportRepository sportRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public Sport saveNewSport(String name, String placeName ,Months start, Months end, Integer price){
        Place place = placeRepository.findByName(placeName);
        Sport sport = new Sport(name, start.toString(), end.toString(), price, place);
        sportRepository.save(sport);
        return sport;
    }

    public Integer bookSportAtPlace(String sportName, String placeName, String locationName, Long startTime, Long endTime){
        LocalDateTime dateTimeStart = LocalDateTime.ofEpochSecond(startTime, 0, ZoneOffset.UTC);
        LocalDateTime dateTimeEnd = LocalDateTime.ofEpochSecond(endTime, 0, ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        String formattedDateStart = dateTimeStart.format(formatter);
        LocalDate startDate = LocalDate.parse(formattedDateStart);

        String formattedDateEnd = dateTimeEnd.format(formatter);
        LocalDate endDate = LocalDate.parse(formattedDateEnd);

        Sport sport = sportRepository.findFirstBySportName(sportName);
        Place place = placeRepository.findByName(placeName);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM");

        List<String> monthsBetweenDates = Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1)))
                .map(date -> date.format(outputFormatter))
                .collect(Collectors.toList());

        List<String> availableMonths = new ArrayList<>();

        int startMonthIndex = Months.valueOf(sport.getStartMonth()).getValue() -1;
        int endMonthIndex =  Months.valueOf(sport.getEndMonth()).getValue();
        int pricePerDay = sport.getPricePerDay();


        if(startMonthIndex > endMonthIndex){
            for(int i = startMonthIndex; i < 12; i++)
                availableMonths.add( Months.values()[i].name());

            for(int i = 0; i < endMonthIndex; i++)
                availableMonths.add( Months.values()[i].name());
        }
        else {
            for(int i = startMonthIndex; i < endMonthIndex; i++)
                availableMonths.add( Months.values()[i].name());
        }


        for(String s : availableMonths){
            if(monthsBetweenDates.indexOf(s) == -1) return -1;
        }

        int daysAtLocation = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            daysAtLocation++;
        }
        return daysAtLocation * pricePerDay;
    }

    boolean contains(List<?> list, List<?> sublist) {
        return Collections.indexOfSubList(list, sublist) != -1;
    }
}
