package io.egen.api.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import io.egen.api.entity.Record;
import io.egen.api.repository.RecordRepositoryCustomized;

public class RecordRepositoryImpl implements RecordRepositoryCustomized{
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Record customStoreRecord(Record record) {
		em.persist(record.getWind());
		em.persist(record);
		return record;
	}

	@Override
	public Record customCityRecord(String city) {
		TypedQuery<Record> query=em.createQuery("SELECT r FROM Record r WHERE r.city=:city ORDER BY r.timestamp DESC", Record.class);
		query.setParameter("city", city);
		List<Record> list=query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}


	
	@Override
	public String customWeatherParam(String city, String param) {
		
		TypedQuery<Record> query=em.createQuery("SELECT r FROM Record r WHERE r.city=:city ORDER BY r.timestamp DESC", Record.class);
		query.setParameter("city", city);
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
	public String customHourlyAvg(String city) {
		Query query=em.createQuery("SELECT AVG(r.temperature) FROM Record r WHERE r.city=:city");
		query.setParameter("city", city);
		return query.getSingleResult().toString();
	}


	@Override
	public String customDailyWeather(String city) {
		
		TypedQuery<String> query=em.createQuery(" SELECT r.description FROM Record r WHERE r.city=:city", String.class);
		query.setParameter("city", city);
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
