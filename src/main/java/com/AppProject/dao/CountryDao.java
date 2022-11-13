package com.AppProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.AppProject.bean.Country;

public class CountryDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root@1234";
	private  String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	public CountryDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertCountry(Country country) throws SQLException {
		System.out.println(Constants.INSERT_DATA_INTO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_DATA_INTO_SQL)) {
			preparedStatement.setString(1, country.getCountry());
			preparedStatement.setInt(2, country.getTotal_case());
			preparedStatement.setInt(3, country.getTotal_death());
			preparedStatement.setInt(4, country.getTotal_recovered());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Country selectCountry(String coun) {
		Country coun_t = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_DATA_BY_COUNTRY);) {
			preparedStatement.setString(1, coun);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String country = rs.getString("country");
				int total_case = rs.getInt("total_case");
				int total_death = rs.getInt("total_death");
				int total_recovered = rs.getInt("total_recovered");
				coun_t = new Country(country, total_case, total_death, total_recovered);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return coun_t;
	}
	
	public List<Country> selectAllCountries() {

		
		List<Country> con = new ArrayList<>();
		try (Connection connection = getConnection();	
			PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_ALL_DATA);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String country = rs.getString("country");
				int total_case = rs.getInt("total_case");
				int total_death = rs.getInt("total_death");
				int total_recovered = rs.getInt("total_recovered");
				con.add(new Country(country, total_case, total_death, total_recovered));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return con;
	}
	
	public boolean updateCountry(Country country) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_DATA_OF_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setInt(1, country.getTotal_case());
			statement.setInt(2, country.getTotal_death());
			statement.setInt(3, country.getTotal_recovered());
			statement.setString(4, country.getCountry());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean deleteCountry(String country) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Constants.DELETE_DATA_FROM_SQL);) {
			statement.setString(1, country);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
