package com.testapp.busbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class City {

	public City(long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue
	private long id;
	@Column(length = 100)
	private String name;
}
