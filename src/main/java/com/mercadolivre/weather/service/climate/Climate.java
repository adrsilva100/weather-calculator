package com.mercadolivre.weather.service.climate;

import com.mercadolivre.weather.dto.ClimateDays;
import com.mercadolivre.weather.dto.ClimateDto;
import com.mercadolivre.weather.dto.DaysDto;

public interface Climate {

    ClimateDto findClimateByDay(final Integer day);

    DaysDto findAmountOfDays(final String climate);
}
