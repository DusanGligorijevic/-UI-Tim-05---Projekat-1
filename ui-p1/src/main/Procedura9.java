package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Procedura9 {

	public static void main(String[] args) {

		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventureworks", "root", "root");
			
			statement = connection.prepareCall("{call zadatak9(?)}");
			
			statement.setString(1, "LL Headset");
			
			statement.execute();
			
			resultSet =statement.getResultSet();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("customerID") + " " + resultSet.getString("name") + " "+ resultSet.getString("state"));
			}
			
			
			if(resultSet != null) resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(resultSet != null) {
				try {
					resultSet.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
	}

  }
}

