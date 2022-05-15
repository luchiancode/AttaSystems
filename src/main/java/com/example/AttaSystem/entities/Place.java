package com.example.AttaSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "place")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sport> sports;

    public Place(String name) {
        this.name = name;
    }

    public Place() { }
}
