package io.egen.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.egen.api.entity.Record;

@Repository
public interface RecordRepository extends CrudRepository<Record,String>, RecordRepositoryCustomized{
	
		public Record customStoreRecord(Record record);
		
		//public List<Record> findAll();
		
		//public Optional<Record> findTopByCity(String city);
		
		@Query("SELECT DISTINCT r.city FROM Record r ORDER BY r.city ASC")
		public List<String> findDistinctCitiesIgnoreCase();
		
		
		public Record customCityRecord(String city);
		
		
		public String customWeatherParam(String city, String param);
		
		
		public String customHourlyAvg(String city);
			
		
		public String customDailyWeather(String city);
		
}
