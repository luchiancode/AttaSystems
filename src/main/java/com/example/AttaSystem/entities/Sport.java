package com.example.AttaSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "sport")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sport_name")
    private String sportName;

    @Column(name = "start_month")
    String startMonth;

    @Column(name = "end_month")
    String endMonth;

    @Column(name = "price_per_day")
    Integer pricePerDay;


    public Sport(String sportName, String startMonth, String endMonth, Integer pricePerDay, Place place) {
        this.sportName = sportName;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.pricePerDay = pricePerDay;
        this.place = place;
    }

    public Sport () {}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place place;
}




