package com.mercadolivre.weather.controller;

import com.mercadolivre.weather.dto.ClimateDays;
import com.mercadolivre.weather.dto.ClimateDto;
import com.mercadolivre.weather.dto.DaysDto;
import com.mercadolivre.weather.entity.ClimateDay;
import com.mercadolivre.weather.service.calculator.WheaterCalculatorService;
import com.mercadolivre.weather.service.climate.ClimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wheater/v1")
public class WheaterCalculatorController {

    private WheaterCalculatorService wheaterCalculatorService;
    private ClimateService climateService;

    @Autowired
    public WheaterCalculatorController(WheaterCalculatorService wheaterCalculatorService,
                                       ClimateService climateService) {
        this.wheaterCalculatorService = wheaterCalculatorService;
        this.climateService = climateService;
    }

    @PostMapping(value = "/initialize")
    public ResponseEntity initializeDatabase() {
        if(wheaterCalculatorService.calculateWheatersByDays(3650)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping(value = "/climate")
    public ResponseEntity<ClimateDto> findClimateByDay(@RequestParam final Integer day) {
        return ResponseEntity.ok(climateService.findClimateByDay(day));
    }

    @GetMapping(value = "/amountofdays")
    public ResponseEntity<DaysDto> amountOfDays(@RequestParam final String climate) {
        return ResponseEntity.ok(climateService.findAmountOfDays(climate));
    }
}
