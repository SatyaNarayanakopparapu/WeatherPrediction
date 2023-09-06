package com.app.weather.weatherPrediction.service;

import com.alicp.jetcache.anno.Cached;
import com.app.weather.weatherPrediction.dto.WeatherAverageDTO;
import com.app.weather.weatherPrediction.dto.WeatherDetails;
import com.app.weather.weatherPrediction.dto.WeatherMapDTO;
import com.app.weather.weatherPrediction.dto.WeatherMapTimeDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class WeatherService {
    private final String URI = "http://api.openweathermap.org/data/2.5/forecast";
    private final String API_ID = "d2929e9483efc82c82c32ee7e02d563e";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cached(expire = 10, timeUnit = TimeUnit.MINUTES)
    public ResponseEntity<?> weatherForecastAverage(String city) {
        List<WeatherDetails> result = new ArrayList<WeatherDetails>();
        WeatherMapDTO weatherMap;
        try {
            getWeatherAverageData(city, result);

        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new Json(e.getResponseBodyAsString()), e.getStatusCode());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public void getWeatherAverageData(String city, List<WeatherDetails> result) {
        WeatherMapDTO weatherMap;
        weatherMap = this.restTemplate.getForObject(this.url(city), WeatherMapDTO.class);
       /* for (LocalDate reference = LocalDate.now();
             reference.isBefore(LocalDate.now().plusDays(4));
             reference = reference.plusDays(1)) {
            final LocalDate ref = reference;
            List<WeatherMapTimeDTO> collect = weatherMap.getList().stream()
                    .filter(x -> x.getDt().toLocalDate().equals(ref)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(collect)) {
                result.add(this.average(collect));
            }
        }*/

        List<WeatherDetails>  weatherDetailsList = new ArrayList<>();
        for(WeatherMapTimeDTO weatherMapTimeDTO : weatherMap.getList() ){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            WeatherDetails weatherDetails = new WeatherDetails();
            weatherDetails.setTemp(weatherMapTimeDTO.getMain().getTemp());
            weatherDetails.setTemp_max(weatherMapTimeDTO.getMain().getTemp_max());
            weatherDetails.setTemp_min(weatherMapTimeDTO.getMain().getTemp_min());
            weatherDetails.setWeatherDescription(weatherMapTimeDTO.getWeather().get(0).getDescription());
            weatherDetails.setSpeed(weatherMapTimeDTO.getWind().getSpeed());
            weatherDetails.setDate(weatherMapTimeDTO.getDt().format(dtf));
            weatherDetails.setHumidity(weatherMapTimeDTO.getMain().getHumidity());
            weatherDetails.setPressure(weatherMapTimeDTO.getMain().getPressure());

            StringBuilder msg = new StringBuilder();
            if(weatherMapTimeDTO.getMain().getTemp_max().compareTo(new BigDecimal(40)) == 1) {
                msg.append("Use Sunscreen Lotion");
            }
            if(weatherMapTimeDTO.getWind().getSpeed().compareTo(new BigDecimal(10) ) == 1){
                msg.append("\n It’s too windy, watch out!");
            }
            if(weatherMapTimeDTO.getWeather().get(0).getId().toString().startsWith("2")) {
                msg.append("\n Don’t step out! A Storm is brewing!");
            }
            if(weatherMapTimeDTO.getWeather().get(0).getId().toString().startsWith("5")) {
                msg.append("\n Carry umbrella");
            }
            weatherDetails.setSuggestions(msg.toString());
            weatherDetailsList.add(weatherDetails);
        }

            result.addAll(weatherDetailsList);

    }

    private WeatherAverageDTO average(List<WeatherMapTimeDTO> list) {
        WeatherAverageDTO result = new WeatherAverageDTO();

        for (WeatherMapTimeDTO item : list) {
            result.setDate(item.getDt().toLocalDate());
            result.plusMap(item);
        }

        result.totalize();

        return result;
    }

    private String url(String city) {
        return String.format(URI.concat("?q=%s").concat("&appid=%s").concat("&units=metric").concat("&cnt=30"), city, API_ID);
    }
}
