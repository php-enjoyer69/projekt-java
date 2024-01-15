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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePK id_role;

	@Column(name="is_director")
	private byte is_director;

	public Role() {
	}

	public RolePK getId_role() {
		return this.id_role;
	}

	public void setId_role(RolePK id_role) {
		this.id_role = id_role;
	}

	public byte getIs_director() {
		return this.is_director;
	}

	public void setIs_director(byte is_director) {
		this.is_director = is_director;
	}

}