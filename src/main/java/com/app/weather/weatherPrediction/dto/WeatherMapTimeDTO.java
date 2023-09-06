package com.app.weather.weatherPrediction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherMapTimeDTO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dt_txt")
	private LocalDateTime dt;

	private WeatherMapTimeMainDTO main;

	public List<WeatherMapCloudDTO> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherMapCloudDTO> weather) {
		this.weather = weather;
	}

	private List<WeatherMapCloudDTO> weather;

    public WeatherMapCloudDetailDTO getClouds() {
        return clouds;
    }

    public void setClouds(WeatherMapCloudDetailDTO clouds) {
        this.clouds = clouds;
    }

    private WeatherMapCloudDetailDTO clouds;



	public WeatherMapWindDTO getWind() {
		return wind;
	}

	public void setWind(WeatherMapWindDTO wind) {
		this.wind = wind;
	}

	private WeatherMapWindDTO wind;



	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	public WeatherMapTimeMainDTO getMain() {
		return main;
	}

	public void setMain(WeatherMapTimeMainDTO main) {
		this.main = main;
	}

	@JsonIgnore
	public Boolean isDaily() {
		return (this.dt.getHour() >= 6 && this.dt.getHour() < 18);
	}

}
