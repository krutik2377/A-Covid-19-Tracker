package com.AppProject.dao;

public class Constants {
	
	
	public static final String INSERT_DATA_INTO_SQL = "INSERT INTO covid" + "  (country, total_case, total_death, total_recovered) VALUES "
			+ " (?, ?, ?, ?);";

	public static final String SELECT_DATA_BY_COUNTRY = "select country,total_case,total_death,total_recovered from covid where country =?";
	public static final String SELECT_ALL_DATA = "select * from covid";
	public static final String DELETE_DATA_FROM_SQL = "delete from covid where country = ?;";
	public static final String UPDATE_DATA_OF_SQL = "update covid set total_case = ?, total_death =?, total_recovered =? where country = ?;";
}
