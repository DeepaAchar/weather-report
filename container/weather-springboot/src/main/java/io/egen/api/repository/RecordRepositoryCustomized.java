package io.egen.api.repository;

import io.egen.api.entity.Record;

public interface RecordRepositoryCustomized {
	
	public Record customStoreRecord(Record record);
	public Record customCityRecord(String city);
	public String customWeatherParam(String city, String param);
	public String customHourlyAvg(String city);
	public String customDailyWeather(String city);
	
}
