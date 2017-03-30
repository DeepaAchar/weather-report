package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Record;

public interface RecordRepository {
	
	public Record storeRecords(Record records);
	public List<Record> findAll();
	public Record findRecord(String location);
	public List<String> findRecordedCities();
	public Record findLatestWeather(String location);
	public String findSelectedWeather(String location,String param);
	public String hourlyAvgTemp(String location);
	public String dailyAvgWeather(String location);

}
