package com.hackerrank.weather.mapper;

import com.hackerrank.weather.entity.Weather;
import com.hackerrank.weather.model.WeatherRequest;
import com.hackerrank.weather.model.WeatherResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WeatherMapper {

    public abstract Weather toWeather(WeatherRequest dto);

    public abstract WeatherResponse toWeatherResponse(Weather dto);

    public abstract List<WeatherResponse> toWeatherResponseList(List<Weather> dto);
}
