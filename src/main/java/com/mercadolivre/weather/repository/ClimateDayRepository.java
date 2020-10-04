package com.mercadolivre.weather.repository;

import com.mercadolivre.weather.entity.ClimateDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimateDayRepository extends CrudRepository<ClimateDay, Long> {

    ClimateDay findByNumberOfDay(final int day);

    Long countByClimate(final String climate);

    List<ClimateDay> findAllByClimate(final String climate);
}
