package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePK id;

	@Column(name="is_director")
	private byte isDirector;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="idmovie", insertable=false, updatable=false)
	private Movie movie;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="idperson", insertable=false, updatable=false)
	private Person person;

	public Role() {
	}

	public RolePK getId() {
		return this.id;
	}

	public void setId(RolePK id) {
		this.id = id;
	}

	public byte getIsDirector() {
		return this.isDirector;
	}

	public void setIsDirector(byte isDirector) {
		this.isDirector = isDirector;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}