package com.mercadolivre.weather.service.climate;

import com.mercadolivre.weather.dto.ClimateDays;
import com.mercadolivre.weather.dto.ClimateDto;
import com.mercadolivre.weather.dto.DaysDto;
import com.mercadolivre.weather.entity.ClimateDay;
import com.mercadolivre.weather.exceptions.ClimateValidationException;
import com.mercadolivre.weather.exceptions.NotFoundClimateException;
import com.mercadolivre.weather.repository.ClimateDayRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.mercadolivre.weather.dto.ClimateDays.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClimateServiceTest {

    @InjectMocks
    private ClimateService climateService;

    @Mock
    private ClimateDayRepository climateDayRepository;

    @Test
    public void thanReturnClimateByDay() {
        ClimateDay climateDay = new ClimateDay();
        climateDay.setId(566L);
        climateDay.setClimate("RAIN");
        climateDay.setNumberOfDay(566);
        climateDay.setPerimeter(0.6847d);
        when(climateDayRepository.findByNumberOfDay(0)).thenReturn(climateDay);
        ClimateDto climateDto = climateService.findClimateByDay(0);

        assertEquals(566, climateDto.getDay());
        assertEquals("RAIN", climateDto.getClimate());
    }

    @Test(expected = NotFoundClimateException.class)
    public void thanReturnExceptionWhenNotFoundDay(){
        when(climateDayRepository.findByNumberOfDay(0)).thenReturn(null);
        climateService.findClimateByDay(0);
    }

    @Test
    public void thanReturnCountByFindForDryDays() {
        when(climateDayRepository.countByClimate(DRY.toString())).thenReturn(11l);
        DaysDto day = climateService.findAmountOfDays(DRY.toString());

        assertEquals(11, day.getAmountOfDays());
    }

    @Test
    public void thanReturnCountByFindForGreatDays() {
        when(climateDayRepository.countByClimate(GREAT.toString())).thenReturn(0l);
        DaysDto day = climateService.findAmountOfDays(GREAT.toString());

        assertEquals(0, day.getAmountOfDays());
    }

    @Test(expected = ClimateValidationException.class)
    public void thanReturnExceptionWhenNotFoundParameterClimate(){
        climateService.findAmountOfDays("Rains");
    }

    @Test
    public void thanReturnCountByFindForRainDays() {
        List<ClimateDay> climateDays = new ArrayList<>();
        ClimateDay climateDay = new ClimateDay();
        climateDay.setId(566L);
        climateDay.setClimate("RAIN");
        climateDay.setNumberOfDay(566);
        climateDay.setPerimeter(0.6847d);
        climateDays.add(climateDay);
        climateDay = new ClimateDay();
        climateDay.setId(567L);
        climateDay.setClimate("RAIN");
        climateDay.setNumberOfDay(567);
        climateDay.setPerimeter(0.622347d);
        climateDays.add(climateDay);
        climateDay = new ClimateDay();
        climateDay.setId(568L);
        climateDay.setClimate("RAIN");
        climateDay.setNumberOfDay(568);
        climateDay.setPerimeter(0.123d);
        climateDays.add(climateDay);

        when(climateDayRepository.findAllByClimate(RAIN.toString())).thenReturn(climateDays);
        DaysDto day = climateService.findAmountOfDays(RAIN.toString());

        assertEquals(3, day.getAmountOfDays());
        assertEquals(566, day.getNumberOfDay());
    }

    @Test(expected = NotFoundClimateException.class)
    public void thanReturnExceptionWhenNotFindRainDays(){
        when(climateDayRepository.findAllByClimate(RAIN.toString())).thenReturn(null);
        climateService.findAmountOfDays(RAIN.toString());
    }
}
