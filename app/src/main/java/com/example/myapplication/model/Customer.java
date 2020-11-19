package com.example.myapplication.model;

import java.io.Serializable;

public class Customer implements Serializable{
	private int id;
	private String username;
	private String pass;
	private String name;
	private String phone;
	private String email;
	private String city;

	public Customer() {
	}

	public Customer(int id, String username, String pass, String name, String phone, String email, String city) {
		this.username = username;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
