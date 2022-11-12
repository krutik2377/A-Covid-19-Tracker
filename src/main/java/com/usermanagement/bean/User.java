package com.usermanagement.bean;

public class User {
	
	private String country;
	private int total_case;
	private int total_death;
	private int total_recovered;
	
	
	public User(String country, int total_case, int total_death, int total_recovered) {
		super();
		this.country = country;
		this.total_case = total_case;
		this.total_death = total_death;
		this.total_recovered = total_recovered;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getTotal_case() {
		return total_case;
	}


	public void setTotal_case(int total_case) {
		this.total_case = total_case;
	}


	public int getTotal_death() {
		return total_death;
	}


	public void setTotal_death(int total_death) {
		this.total_death = total_death;
	}


	public int getTotal_recovered() {
		return total_recovered;
	}


	public void setTotal_recovered(int total_recovered) {
		this.total_recovered = total_recovered;
	}
	
}
