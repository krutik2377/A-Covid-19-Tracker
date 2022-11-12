package com.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usermanagement.bean.User;

public class UserDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/users ";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root@1234";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static final String INSERT_DATA_INTO_SQL = "INSERT INTO covid" + "  (country, total_case, total_death, total_recovered) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_DATA_BY_COUNTRY = "select country,total_case,total_death,total_recovered from covid where country =?";
	private static final String SELECT_ALL_DATA = "select * from covid";
	private static final String DELETE_DATA_FROM_SQL = "delete from covid where country = ?;";
	private static final String UPDATE_DATA_OF_SQL = "update covid set total_case = ?, total_death =?, total_recovered =? where country = ?;";
	
	public UserDao() {
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
	
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_DATA_INTO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_INTO_SQL)) {
			preparedStatement.setString(1, user.getCountry());
			preparedStatement.setInt(2, user.getTotal_case());
			preparedStatement.setInt(3, user.getTotal_death());
			preparedStatement.setInt(4, user.getTotal_recovered());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public User selectUser(String coun) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DATA_BY_COUNTRY);) {
			preparedStatement.setString(1, coun);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String country = rs.getString("country");
				int total_case = rs.getInt("total_case");
				int total_death = rs.getInt("total_death");
				int total_recovered = rs.getInt("total_recovered");
				user = new User(country, total_case, total_death, total_recovered);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DATA);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String country = rs.getString("country");
				int total_case = rs.getInt("total_case");
				int total_death = rs.getInt("total_death");
				int total_recovered = rs.getInt("total_recovered");
				users.add(new User(country, total_case, total_death, total_recovered));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DATA_OF_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setInt(1, user.getTotal_case());
			statement.setInt(2, user.getTotal_death());
			statement.setInt(3, user.getTotal_recovered());
			statement.setString(4, user.getCountry());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean deleteUser(String country) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DATA_FROM_SQL);) {
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
