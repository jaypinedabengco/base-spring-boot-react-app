package com.bengco.app.react.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.bengco.app.react.model.abstracts.DateAudit;

@Entity
@Table(name = "user")
public class User extends DateAudit {

	private static final long serialVersionUID = -1390789652487237899L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(max = 40)
	@Column(name = "name")
	private String name;

	@NotBlank
	@Size(max = 15)
	@Column(name = "username", unique = true)
	private String username;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	@Column(name = "email", unique = true)
	private String email;

	@NotBlank
	@Size(max = 100)
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {

	}

	public User(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 15) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
