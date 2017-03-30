package io.egen.api.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.repository.RecordRepository;
import io.egen.api.entity.Record;

@Repository
public class RecordRepositoryImpl implements RecordRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Record storeRecords(Record records) {
			em.persist(records.getWind());
			em.persist(records);
		return records;
	}

	@Override
	public List<Record> findAll() {
		TypedQuery<Record> query=em.createNamedQuery("Record.findAll", Record.class);
		return query.getResultList();
	}
	
	@Override
	public Record findRecord(String id) {
		return em.find(Record.class,id);		
	}

	@Override
	public List<String> findRecordedCities() {
		TypedQuery<String> query=em.createNamedQuery("Record.findRecordedCities",String.class);
		List<String> list= query.getResultList();
		if(list.isEmpty()){
			return null;
		}				
		return list;		
	}

	@Override
	public Record findLatestWeather(String location) {
		TypedQuery<Record> query=em.createNamedQuery("Record.findLatestWeather", Record.class);
		query.setParameter("pcity", location.toString());
		List<Record> list=query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public String findSelectedWeather(String location, String param) {
		TypedQuery<Record> query=em.createNamedQuery("Record.findLatestWeather", Record.class);
		query.setParameter("pcity",location);
		List<Record> list= query.getResultList();
		Record record=list.get(0);
		
		if(list.isEmpty()){
			return "";
		}
		
		if(param.toLowerCase().compareTo("description")==0){
			return record.getDescription().toString();
		}
		else if(param.toLowerCase().compareTo("humidity")==0){
			return Double.toString(record.getHumidity());
		}
		else if(param.toLowerCase().compareTo("pressure")==0){
			return Double.toString(record.getPressure());
		}
		else if(param.toLowerCase().compareTo("temperature")==0){
			return Double.toString(record.getTemperature());
		}
		else if(param.toLowerCase().compareTo("wind")==0){
			return Double.toString(record.getWind().getSpeed()) + ", "+
					Double.toString(record.getWind().getDegree());
		}
		else
			return record.toString();
	}

	@Override
	public String hourlyAvgTemp(String location) {
		Query query=em.createNamedQuery("Record.hourlyAvgTemp");
		query.setParameter("pcity",location);
		return query.getSingleResult().toString();
		
	}

	@Override
	public String dailyAvgWeather(String location) {
		TypedQuery<String> query=em.createNamedQuery("Record.dailyAvgWeather", String.class);
		query.setParameter("pcity",location);
		List<String> list= query.getResultList();
		String report="UNKNOWN";
		if(list.isEmpty()){
			return null;
		}
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).compareToIgnoreCase("light rain")==0||(list.get(i).compareToIgnoreCase("moderate rain")==0)){
				report="Rainy";
				return report;
			}
		}
		for(int i=0;i<list.size();i++){
			if((list.get(i).compareToIgnoreCase("mist")==0)||(list.get(i).compareToIgnoreCase("fog")==0)){
				report="Cold";
				return report;
			}
		}
		
		report="Sunny";
		return report;
	}

}

