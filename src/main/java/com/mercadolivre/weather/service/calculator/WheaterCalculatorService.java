package com.mercadolivre.weather.service.calculator;

import com.mercadolivre.weather.dto.PlanetDto;
import com.mercadolivre.weather.dto.PointDto;
import com.mercadolivre.weather.dto.TriangleDto;
import com.mercadolivre.weather.entity.ClimateDay;
import com.mercadolivre.weather.repository.ClimateDayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mercadolivre.weather.dto.ClimateDays.*;

@Slf4j
@Service
public class WheaterCalculatorService implements WheaterCalculator {

    private final PlanetDto vulcanos = new PlanetDto("Vulcano", 1000f, 5, false);
    private final PlanetDto ferengi = new PlanetDto("Ferengi", 500f, 1, true);
    private final PlanetDto betasoide = new PlanetDto("Betasoide", 2000f, 3, true);
    private final PlanetDto sum = new PlanetDto("Sum", 0f, 0);
    private ClimateDayRepository climateDayRepository;

    @Autowired
    public WheaterCalculatorService(ClimateDayRepository climateDayRepository) {
        this.climateDayRepository = climateDayRepository;
    }

    @Override
    public boolean calculateWheatersByDays(final int days) {
        log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=Initialize wheater calculator for " + days + " days");
        try {
            climateDayRepository.deleteAll();
            log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=All data has been deleted");
            for (int day = 0; day < days; day++) {
                PointDto pointSum = sum.getPoint(day);
                TriangleDto triangle = new TriangleDto(vulcanos.getPoint(day), ferengi.getPoint(day), betasoide.getPoint(day));
                if (triangle.getArea() == 0) {
                    if (triangle.isExistPoint(pointSum)) {
                        log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=day " + day + " climate=dry");
                        climateDayRepository.save(new ClimateDay(day, DRY.toString(), triangle.getPerimeter()));
                    } else {
                        log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=day " + day + " climate=great");
                        climateDayRepository.save(new ClimateDay(day, GREAT.toString(), triangle.getPerimeter()));
                    }
                } else if (triangle.isExistPoint(pointSum)) {
                    log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=day " + day + " climate=rain");
                    climateDayRepository.save(new ClimateDay(day, RAIN.toString(), triangle.getPerimeter()));
                } else {
                    log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=day " + day + " climate=normal");
                    climateDayRepository.save(new ClimateDay(day, NORMAL.toString(), triangle.getPerimeter()));
                }
            }
        } catch (Exception e) {
            log.error("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=Cant calculate climate, exception=" + e.getMessage());
            return false;
        }
        log.info("method={calculateWheatersByDays}, class={WheaterCalculatorService}, message=Processing finished with success");
        return true;
    }
}
