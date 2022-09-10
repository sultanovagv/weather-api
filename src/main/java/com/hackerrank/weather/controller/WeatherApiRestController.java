package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.WeatherRequest;
import com.hackerrank.weather.model.WeatherResponse;
import com.hackerrank.weather.model.WeatherSearchFilter;
import com.hackerrank.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/weather")
@RequiredArgsConstructor
@RestController
public class WeatherApiRestController {

    private final WeatherService service;

    @PostMapping
    public ResponseEntity<WeatherResponse> create(@Valid @RequestBody WeatherRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public List<WeatherResponse> findAll(WeatherSearchFilter filter) {
        return service.findAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherResponse> findById(@PathVariable Integer id) {
        return service.findWeatherById(id);
    }
}
