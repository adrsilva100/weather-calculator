package com.mercadolivre.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CLIMATE_DAY")
public class ClimateDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER_OF_DAY", nullable = false)
    private int numberOfDay;

    @Column(name = "CLIMATE", nullable = false)
    private String climate;

    @Column(name = "PERIMITER", nullable = false)
    private Double perimeter;

    public ClimateDay(final int numberOfDay, final String climate, final Double perimeter) {
        this.numberOfDay = numberOfDay;
        this.climate = climate;
        this.perimeter = perimeter;
    }
}
