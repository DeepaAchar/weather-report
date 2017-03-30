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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController //@Controller + @ResponseBody
@Api
//Class for url-service mapping
public class RecordController {
	
	//Constructor based injection for service layer
	private RecordService service;
	public RecordController(RecordService service){
		this.service=service;
	}
	
	//Post weather reading from http://mocker.egen.io to database
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, value=URI.RECORDS,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ApiOperation(value = "Post weather reading from http://mocker.egen.io to database", 
					notes="Returns JSON ResponseBody and stores to database")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Load Successful from mocker.egen.io"),
			@ApiResponse(code = 404, message = "The requested resource does not exist on the server"),
			@ApiResponse(code = 500, message = "An internal error occurred on the server. This may be because of an application error or configuration problem")
	})
	public Record storeRecords(@RequestBody Record records){
		return service.storeRecords(records);
	}
		
	//List cities recorded in the schema weather-dB
	@RequestMapping(method=RequestMethod.GET, value=URI.CITIES,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ApiOperation(value = "Get recorded cities in database", 
					notes="Returns list cities recorded in the schema weather-dB")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Load Successful"),
			@ApiResponse(code = 404, message = "The requested city record does not exist on the server"),
			@ApiResponse(code = 500, message = "An internal error occurred on the server. This may be because of an application error or configuration problem")
	})
	public List<String> findRecordedCities(){
		return service.findRecordedCities();
		
	}
	
	//Get latest weather for a city
	@RequestMapping(method=RequestMethod.GET, value=URI.CITY,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ApiOperation(value = "Get latest weather for a city", 
					notes="Returns latest recorded weather report for given city from schema database")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Load Successful"),
			@ApiResponse(code = 404, message = "The rrequested city record does not exist on the server"),
			@ApiResponse(code = 500, message = "An internal error occurred on the server. This may be because of an application error or configuration problem")
	})
	public Record findLatestWeather(@PathVariable("city") String reqCity){
		return service.findLatestWeather(reqCity);
	}
	
	//Get selected parameter of latest weather for a city
	@RequestMapping(method=RequestMethod.GET, value=URI.CITYPARAM,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ApiOperation(value = "Get requested parameter of latest weather report for a city", 
					notes="Returns one requested parameter:"
			+ "description,humidity,pressure,temperature,wind,any other string(:all the parameters) "
			+ "for latest recorded weather report for given city from schema database")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Load Successful"),
			@ApiResponse(code = 404, message = "The requested city record does not exist on the server"),
			@ApiResponse(code = 500, message = "An internal error occurred on the server. This may be because of an application error or configuration problem")
	})
	public String findSelectedWeather(@PathVariable("city") String location,@PathVariable("param") String param){
		return service.findSelectedWeather(location, param);
	}
	
	//Get hourly average weather for a city
	@RequestMapping(method=RequestMethod.GET, value=URI.HOURLY,produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ApiOperation(value = "Get hourly average weather for a city", 
					notes="Returns average temperature of recorded weather report over an hour for given city from database")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Load Successful"),
			@ApiResponse(code = 404, message = "The requested city record does not exist on the server"),
			@ApiResponse(code = 500, message = "An internal error occurred on the server. This may be because of an application error or configuration problem")
	})
	public String hourlyAvgTemp(@PathVariable("city") String location){
		return service.hourlyAvgTemp(location);
		
	}
	
	//Get day weather condition for a city depending on stored description for a record
	@RequestMapping(method=RequestMethod.GET, value=URI.DAILY,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Get day weather condition for a city depending on stored description for a record", 
					notes="Returns overall description of recorded weather report like sunny, rainy or cold  for a day for given city from database")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Load Successful"),
			@ApiResponse(code = 404, message = "The requested city record does not exist on the server"),
			@ApiResponse(code = 500, message = "An internal error occurred on the server. This may be because of an application error or configuration problem")
	})
	public String dailyAvgWeather(@PathVariable("city") String location){
		return service.dailyAvgWeather(location);
	}
}
