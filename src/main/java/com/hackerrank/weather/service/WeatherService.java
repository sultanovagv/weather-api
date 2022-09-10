package com.hackerrank.weather.service;

import com.hackerrank.weather.entity.Weather;
import com.hackerrank.weather.mapper.WeatherMapper;
import com.hackerrank.weather.model.WeatherRequest;
import com.hackerrank.weather.model.WeatherResponse;
import com.hackerrank.weather.model.WeatherSearchFilter;
import com.hackerrank.weather.repository.WeatherRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherMapper mapper;

    public WeatherService(WeatherRepository weatherRepository, WeatherMapper mapper) {
        this.weatherRepository = weatherRepository;
        this.mapper = mapper;
    }

    public WeatherResponse create(WeatherRequest request) {
        Weather weather = weatherRepository.save(mapper.toWeather(request));
        return mapper.toWeatherResponse(weather);
    }

    public List<WeatherResponse> findAll(WeatherSearchFilter filter) {
        List<Weather> ls = null;
        if (filter.isCityDefined()) {
            ls = findWeathersByName(filter.getCity());
        }
        if (filter.isSortDefined()) {
            ls = findWeathersBySorting(filter);
        }
        if (filter.isDateDefined()) {
            ls = weatherRepository.findByDate(filter.getDate());
        }
        if (ls == null) {
            ls = weatherRepository.findAll();
        }
        return mapper.toWeatherResponseList(ls);
    }

    public ResponseEntity<WeatherResponse> findWeatherById(Integer id) {
        Optional<Weather> weatherData = weatherRepository.findById(id);
        return weatherData.map(weather -> new ResponseEntity<>(mapper.toWeatherResponse(weather), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private List<Weather> findWeathersByName(List<String> names) {
        List<String> ls = names.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        return weatherRepository.findByCityIn(ls);
    }

    private List<Weather> findWeathersBySorting(WeatherSearchFilter filter) {
        List<Weather> ls;
        if (filter.getSort().startsWith("-")) {
            ls = weatherRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        } else {
            ls = weatherRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
        }
        return ls;
    }


}
