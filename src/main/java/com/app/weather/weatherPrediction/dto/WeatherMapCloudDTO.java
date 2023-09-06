package com.app.weather.weatherPrediction.dto;

import java.math.BigDecimal;

public class WeatherMapCloudDTO {

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private BigDecimal id;

   private String main;

   private String description;

}
