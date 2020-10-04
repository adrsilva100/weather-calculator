package com.mercadolivre.weather.service.calculator;

import com.mercadolivre.weather.repository.ClimateDayRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class WheaterCalculatorServiceTest {

    @Mock
    private ClimateDayRepository climateDayRepository;

    @InjectMocks
    private WheaterCalculatorService wheaterCalculatorService;

    @Test
    public void thanReturnTrueCalculateWheaterByTenYears() {
        assertTrue(wheaterCalculatorService.calculateWheatersByDays(3650));
    }

    @Test
    public void thanReturnExceptionWhenHappendAnyProblem() {
        doThrow(RuntimeException.class).when(climateDayRepository).deleteAll();
        assertFalse(wheaterCalculatorService.calculateWheatersByDays(3650));
    }
}
