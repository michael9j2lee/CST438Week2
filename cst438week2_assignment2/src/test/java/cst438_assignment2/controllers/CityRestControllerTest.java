package cst438_assignment2.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import cst438_assignment2.domain.*;
import cst438_assignment2.weather.*;


//class must be annotated as WebMvcTest,  not SpringBootTest
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {
	
	// declare as @MockBean those classes which will be stubbed in the test
	// These classes must be Spring components (such as Repositories)
	// or @Service classes.

	@MockBean
	private CityService cityService;

	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private WeatherService weatherService;

	// This class is used for make simulated HTTP requests to the class
	// being tested.
	@Autowired
	private MockMvc mvc;
	
	// These objects will be magically initialized by the initFields method below.
 private JacksonTester<CityInfo> jsonCityAttempt;

 // This method is executed before each Test.
	@BeforeEach
	public void setUpEach() {
		MockitoAnnotations.initMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}

	// Have one or more test methods.
	
	//Tests 1 for a fake Test
	@Test
	public void test1() throws Exception {

		Country country = new Country("TestCountryCode","EX");
		City city = new City(0,"TestCity","TestDistrict",0,country);
		TempAndTime t = new TempAndTime(0,1234,4321);
		List<City> cities = new ArrayList<City>();
		cities.add(city);

		// create the stub calls and return data for weather service and cityService
		//  when the getWeather method is called with name parameter "TestCity", 
		//  the stub will return the given temp (in degrees Kelvin) and condition.
		
		given(weatherService.getTempAndTime("TestCity")).willReturn( t );
		given(cityService.getCityInfo("TestCity")).willReturn( new CityInfo(city, t ));
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(cityRepository.findByName("TestCity")).willReturn(cities);

		

		// perform the test by making simulated HTTP get using URL of "/city/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity"))
				.andReturn().getResponse();
				

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// convert returned data from JSON string format to City object
		CityInfo cityInfoResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		CityInfo expectedResult =  new CityInfo(
				new City( 0 ,"TestCity","TestDistrict", 0,
						new Country( "TestCountryCode","EX")),
				new TempAndTime(0,1234,4321)
						);
	
		
		// compare actual return data with expected data
	    // MUST implement .equals( ) method for City class.
		assertThat(cityInfoResult).isEqualTo(expectedResult);
	}
	
	/*
	 * test for a incorrect answer
	 */
	@Test
	public void test2() throws Exception {
		Country country = new Country("TestCountryCode","EX");
		City city = new City(0,"TestCity","TestDistrict",0,country);
		TempAndTime t = new TempAndTime(0,1234,4321);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		
		given(weatherService.getTempAndTime("TestCity")).willReturn( t );
		given(cityService.getCityInfo("TestCity")).willReturn( new CityInfo(city, t ));
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		// perform the test by making simulated HTTP get using URL of "/city/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity"))
				.andReturn().getResponse();
				
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// convert returned data from JSON string format to City object
		CityInfo cityInfoResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		//incorrect answer
		CityInfo expectedResult =  new CityInfo(
				new City( 0 ,"City","District", 0,
						new Country( "CountryCode","FE")),
				new TempAndTime(0,0,0)
						);
		
		assertFalse( cityInfoResult.equals(expectedResult ));
	}
	
	//TODO Test3.  Need to try city with multiple.
}