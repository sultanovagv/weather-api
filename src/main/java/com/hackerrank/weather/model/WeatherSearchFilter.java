package com.hackerrank.weather.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class WeatherSearchFilter {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private List<String> city;

    private String sort;


    public boolean isDateDefined() {
        return Objects.nonNull(date);
    }

    public boolean isCityDefined() {
        return !CollectionUtils.isEmpty(city);
    }

    public boolean isSortDefined() {
        return !StringUtils.isEmpty(sort);
    }


}
