package io.egen.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.URI;
import io.egen.api.entity.Record;
import io.egen.api.service.RecordService;

@RestController //@Controller + @ResponseBody
//@RequestMapping(value=URI.RECORDS)

//Class for url-service mapping
public class RecordController {
	
	//Constructor based injection for service layer
	private RecordService service;
	public RecordController(RecordService service){
		this.service=service;
	}
	
	
	//Get weather reading from http://mocker.egen.io
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, value=URI.RECORDS,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Record storeRecords(@RequestBody Record records){
		return service.storeRecords(records);
	}
		
	//List cities recorded in the schema weather-dB
	@RequestMapping(method=RequestMethod.GET, value=URI.CITIES,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List<String> findRecordedCities(){
		return service.findRecordedCities();
		
	}
	
	//Get latest weather for a city
	@RequestMapping(method=RequestMethod.GET, value=URI.CITY,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Record findLatestWeather(@PathVariable("city") String reqCity){
		return service.findLatestWeather(reqCity);
	}
	
	//Get selected parameter of latest weather for a city
	@RequestMapping(method=RequestMethod.GET, value=URI.CITYPARAM,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public String findSelectedWeather(@PathVariable("city") String location,@PathVariable("param") String param){
		return service.findSelectedWeather(location, param);
	}
	
	//Get hourly average weather for a city
	@RequestMapping(method=RequestMethod.GET, value=URI.HOURLY,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public String hourlyAvgTemp(@PathVariable("city") String location){
		return service.hourlyAvgTemp(location);
		
	}
	
	//Get day weather condition for a city depending on stored description for a record
	@RequestMapping(method=RequestMethod.GET, value=URI.DAILY,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String dailyAvgWeather(@PathVariable("city") String location){
		return service.dailyAvgWeather(location);
	}
	
}
