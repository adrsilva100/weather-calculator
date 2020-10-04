package com.mercadolivre.weather.controller;

import com.mercadolivre.weather.dto.ClimateDto;
import com.mercadolivre.weather.dto.DaysDto;
import com.mercadolivre.weather.service.calculator.WheaterCalculatorService;
import com.mercadolivre.weather.service.climate.ClimateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Initialize that database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "422", description = "Error during processing")})
    @PostMapping(value = "/initialize")
    public ResponseEntity initializeDatabase() {
        if (wheaterCalculatorService.calculateWheatersByDays(3650)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @Operation(summary = "Find of climate by one day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Day not exists in database")})
    @GetMapping(value = "/climate")
    public ResponseEntity<ClimateDto> findClimateByDay(@RequestParam final Integer day) {
        return ResponseEntity.ok(climateService.findClimateByDay(day));
    }

    @Operation(summary = "Find amount of days by type climate (RAIN, DRY, GREAT,  NORMAL)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found amount of days by climate in database"),
            @ApiResponse(responseCode = "400", description = "Climate not exists in database")})
    @GetMapping(value = "/amountofdays")
    public ResponseEntity<DaysDto> amountOfDays(@RequestParam final String climate) {
        return ResponseEntity.ok(climateService.findAmountOfDays(climate));
    }
}
