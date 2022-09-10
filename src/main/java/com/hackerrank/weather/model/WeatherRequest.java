package com.hackerrank.weather.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class WeatherRequest {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private Float lat;
    private Float lon;
    private String city;
    private String state;

    private List<Double> temperatures;
}
