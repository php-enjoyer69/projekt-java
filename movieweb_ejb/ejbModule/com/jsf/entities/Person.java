package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id_person;

	@Temporal(TemporalType.DATE)
	private Date birthyear;

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String surname;
	
	@Column(length = 45)
	private String portrait;

	public void log(String text) {
		System.out.println(text + ": [" + id_person + "], " + name + ", " + surname + ", " + birthyear + ", " + portrait);
	}
	
	public Person() {
	}

	public Integer getId_person() {
		return this.id_person;
	}

	public void setId_person(Integer id_person) {
		this.id_person = id_person;
	}

	public Date getBirthyear() {
		return this.birthyear;
	}

	public void setBirthyear(Date birthyear) {
		this.birthyear = birthyear;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}


}