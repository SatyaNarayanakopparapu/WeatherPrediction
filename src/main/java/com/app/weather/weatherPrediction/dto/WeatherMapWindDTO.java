package com.app.weather.weatherPrediction.dto;

import java.math.BigDecimal;

public class WeatherMapWindDTO {

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getDeg() {
        return deg;
    }

    public void setDeg(BigDecimal deg) {
        this.deg = deg;
    }

    public BigDecimal getGust() {
        return gust;
    }

    public void setGust(BigDecimal gust) {
        this.gust = gust;
    }

    private BigDecimal speed;

    private BigDecimal deg;

    private BigDecimal gust;



}
