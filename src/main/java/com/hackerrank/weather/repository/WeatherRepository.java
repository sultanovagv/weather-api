package com.hackerrank.weather.repository;

import com.hackerrank.weather.entity.Weather;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    @Query("select w from Weather w where lower(w.city) in :cities")
    List<Weather> findByCityIn(@Param("cities") List<String> cities);

    List<Weather> findByDate(Date date);

    List<Weather> findAll(Sort sort);
}
