package com.mercadolivre.weather.service.climate;

import com.mercadolivre.weather.dto.ClimateDays;
import com.mercadolivre.weather.dto.ClimateDto;
import com.mercadolivre.weather.dto.DaysDto;
import com.mercadolivre.weather.entity.ClimateDay;
import com.mercadolivre.weather.exceptions.ClimateValidationException;
import com.mercadolivre.weather.exceptions.NotFoundClimateException;
import com.mercadolivre.weather.repository.ClimateDayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.mercadolivre.weather.dto.ClimateDays.*;

@Slf4j
@Service
public class ClimateService implements Climate {

    private ClimateDayRepository climateDayRepository;

    @Autowired
    public ClimateService(ClimateDayRepository climateDayRepository) {
        this.climateDayRepository = climateDayRepository;
    }

    @Override
    public ClimateDto findClimateByDay(final Integer day) {
        ClimateDay climateDay = climateDayRepository.findByNumberOfDay(day);
        if (climateDay == null) {
            log.info("method={findClimateByDay}, class={ClimateService}, message=Not found climate for day=" + day);
            throw new NotFoundClimateException("Not found climate for day=" + day);
        }
        return new ClimateDto(climateDay.getNumberOfDay(), climateDay.getClimate());
    }

    @Override
    public DaysDto findAmountOfDays(final String climate) {
        ClimateDays climateDays;
        try {
            climateDays = ClimateDays.valueOf(climate.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("method={findAmountOfDays}, class={ClimateService}, message=Parameter climate not found or not valid=" + climate);
            throw new ClimateValidationException("Parameter climate not found or not valid=" + climate);
        }
        if (climateDays == DRY) {
            return new DaysDto(climateDayRepository.countByClimate(DRY.toString()));
        } else if (climateDays == GREAT) {
            return new DaysDto(climateDayRepository.countByClimate(GREAT.toString()));
        } else if (climateDays == RAIN) {
            List<ClimateDay> climates = climateDayRepository.findAllByClimate(RAIN.toString());
            if (climates == null || climates.isEmpty()) {
                log.error("method={findAmountOfDays}, class={ClimateService}, message=Not found climates");
                throw new NotFoundClimateException("Not found climates");
            }
            return new DaysDto(climates.stream().count(), climates.stream().max(Comparator.comparing(ClimateDay::getPerimeter)).get().getNumberOfDay());
        } else {
            log.error("method={findAmountOfDays}, class={ClimateService}, message=Parameter climate not found or not valid=" + climate);
            throw new ClimateValidationException("Parameter climate not found or not valid=" + climate);
        }
    }
}
