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
@Table(name = "movie")
@NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id_movie;

	@Temporal(TemporalType.DATE)
	private Date year;

	@Column(length = 45)
	private String title;

	@Column(length = 300)
	private String description;
	
	@Column(length = 45)
	private String cover;
	
	@Column(length = 45)
	private String id_rating;

	public void log(String text) {
		System.out.println(text + ": [" + id_movie + "], " + title + ", " + year + ", " + description + ", " + cover);
	}
	
	public Movie() {
	}

	public Integer getId_movie() {
		return this.id_movie;
	}

	public void setId_movie(Integer id_movie) {
		this.id_movie = id_movie;
	}

	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;	
	}
	
	public String getId_rating() {
		return this.id_rating;
	}

	public void setId_rating(String id_rating) {
		this.id_rating = id_rating;
	}
	
}