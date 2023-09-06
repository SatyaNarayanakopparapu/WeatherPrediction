package com.app.weather.weatherPrediction.web;


import com.app.weather.weatherPrediction.dto.WeatherAverageDTO;
import com.app.weather.weatherPrediction.dto.WeatherDetails;
import com.app.weather.weatherPrediction.models.FormCityAttribute;
import com.app.weather.weatherPrediction.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class WeatherUIController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/weatherui")
    public ModelAndView cityForm() {
        return new ModelAndView("formData", "command", new FormCityAttribute());
    }

    @GetMapping(value = "/getWeatherDetails")
    public ModelAndView weatherDetails(@ModelAttribute("city") FormCityAttribute formCityAttribute, ModelAndView model) {
        System.out.println("City is {}"+formCityAttribute.getCity());
        List<WeatherDetails> result = new ArrayList<WeatherDetails>();
        weatherService.getWeatherAverageData(formCityAttribute.getCity(),result);
        model.setViewName("weatherDetails");
        model.addObject("items", result);
        return model;

    }


}
