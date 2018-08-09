package com.bengco.app.react.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.bengco.app.react.model.enums.RoleName;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "name", length = 60, unique = true)
	private RoleName name;

	public Role() {
	}

	public Role(RoleName name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

}
