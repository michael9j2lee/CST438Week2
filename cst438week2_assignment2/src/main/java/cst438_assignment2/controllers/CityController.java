package cst438_assignment2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cst438_assignment2.domain.*;
import cst438_assignment2.weather.*;

@Controller
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	WeatherService weatherService;
	
	@Autowired
	CityService cityService;
	
	@GetMapping(value="/city/{name}")
	public String getCity(@PathVariable("name") String name, Model model) {
		
		CityInfo cityInfo = cityService.getCityInfo(name);
		model.addAttribute(cityInfo);
		return "weather_show";
	}
}
