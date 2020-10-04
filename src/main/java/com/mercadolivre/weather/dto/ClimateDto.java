package com.mercadolivre.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClimateDto {

    private int day;
    private String climate;
}
