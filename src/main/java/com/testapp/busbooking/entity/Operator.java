package com.testapp.busbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Operator {

	@Id
	@GeneratedValue
	private long id;
	@Column(length = 100)
	private String name;
}
