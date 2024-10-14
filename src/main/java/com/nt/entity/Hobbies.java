package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hobbies {
	@Id
	private Integer id;
	private String hobbies;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	
	

}
