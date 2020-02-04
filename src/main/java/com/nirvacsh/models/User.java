package com.nirvacsh.models;

public class User {
	
	protected int id;
	protected String name;
	protected String password;
	protected String role;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(int id, String name, String password, String role) {
		this(name, password, role);
		this.id = id;
	}
	
	public User(String name, String password, String role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + "]";
	}
	

}
