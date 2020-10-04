package com.mercadolivre.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DaysDto {

    @JsonProperty(value = "amount_of_days")
    private Long amountOfDays;

    @JsonProperty(value = "number_of_day")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer numberOfDay;

    public DaysDto(final Long amountOfDays) {
        this.amountOfDays = amountOfDays;
    }
}
