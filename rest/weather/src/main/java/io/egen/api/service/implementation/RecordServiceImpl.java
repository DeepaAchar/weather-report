package io.egen.api.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Record;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.RecordRepository;
import io.egen.api.service.RecordService;

@Service
//Class for service-repository mapping, validation
public class RecordServiceImpl implements RecordService{
			
	//Constructor based injection for repository layer
	private RecordRepository repository;
	public RecordServiceImpl(RecordRepository repository){
		this.repository=repository;
	}

	@Override
	@Transactional
	public Record storeRecords(Record records) {
		//logic to read from mocker link and call store method
		return repository.storeRecords(records);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Record> findAll() {
		List<Record> list=repository.findAll();
		if(list==null){
			//throw runtime exception to return ERROR 404: Database is empty !
			throw new NotFoundException("No records exist.dB is Empty! ");
		}
		return list;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Record findRecord(String location) {
		Record record=repository.findRecord(location);
		if(record==null){
			//throw runtime exception to return ERROR 404: No record found!
			throw new NotFoundException("No records exist for user requested location: " + location);
		}
		return record;
	}

	@Override
	@Transactional(readOnly=true)
	public List<String> findRecordedCities() {
		List<String> listCities=repository.findRecordedCities();
		return listCities;
	}

	@Override
	@Transactional(readOnly=true)
	public Record findLatestWeather(String location) {
		
		Record record=repository.findLatestWeather(location);
		
		if(record==null){
			//throw runtime exception to return ERROR 404: No such city is recorded!
			throw new NotFoundException("User requested location: " + location + " is not in records");
		}
		return record;
	}

	@Override
	@Transactional(readOnly=true)
	public String findSelectedWeather(String location, String param) {
		
		String record=repository.findSelectedWeather(location, param);
		
		if(record==null){
				//throw runtime exception to return ERROR 404: No such city is recorded!
				throw new NotFoundException("User requested location: " + location + " is not in records");
			}
			
		return record;
	}

	@Override
	@Transactional(readOnly=true)
	public String hourlyAvgTemp(String location) {
		return repository.hourlyAvgTemp(location);
	}

	@Override
	@Transactional(readOnly=true)
	public String dailyAvgWeather(String location){
		
		String report=repository.dailyAvgWeather(location);
		
		if(report==null){
				//throw runtime exception to return ERROR 404: No such city is recorded!
				throw new NotFoundException("User requested location: " + location + " is not in records");
		}
		return report;
	}

}
