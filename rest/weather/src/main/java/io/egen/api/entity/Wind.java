package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wind {
	@Id
	private String windId;
	private double speed;
	private double degree;
	
	public Wind(){
		this.windId=UUID.randomUUID().toString();
	}
	
	public String getWindId() {
		return windId;
	}
	public void setWindId(String windId) {
		this.windId = windId;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDegree() {
		return degree;
	}
	public void setDegree(double degree) {
		this.degree = degree;
	}
	
	
}
