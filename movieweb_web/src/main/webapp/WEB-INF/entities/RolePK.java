package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The primary key class for the role database table.
 * 
 */
@Embeddable
public class RolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_movie", insertable=false, updatable=false)
	private int id_movie;

	@Column(name="id_person", insertable=false, updatable=false)
	private int id_person;

	public RolePK() {
	}
	public int getId_movie() {
		return this.id_movie;
	}
	public void setId_movie(int id_movie) {
		this.id_movie = id_movie;
	}
	public int getId_person() {
		return this.id_person;
	}
	public void setId_person(int id_person) {
		this.id_person = id_person;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePK)) {
			return false;
		}
		RolePK castOther = (RolePK)other;
		return 
			(this.id_movie == castOther.id_movie)
			&& (this.id_person == castOther.id_person);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id_movie;
		hash = hash * prime + this.id_person;
		
		return hash;
	}
}