package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.OneToMany;
import com.jsf.entities.Role;

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
	private Integer idmovie;

	@Column(length = 4)
	private Integer year;

	@Column(length = 45)
	private String title;

	@Column(length = 300)
	private String description;
	
	@Column(length = 45)
	private String cover;
	
	@Column
	private double rating;

	public void log(String text) {
		System.out.println(text + ": [" + idmovie + "], " + title + ", " + year + ", " + description + ", " + cover);
	}
	
	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="movie")
	private List<Role> roles;
	
	public Movie() {
	}

	public Integer getIdmovie() {
		return this.idmovie;
	}

	public void setIdmovie(Integer idmovie) {
		this.idmovie = idmovie;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
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
	
	public double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setMovie(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setMovie(null);

		return role;
	}
	
}