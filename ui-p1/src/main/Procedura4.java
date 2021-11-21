package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Procedura4 {

	public static void main(String[] args) {
		
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventureworks", "root", "root");
			
			statement = connection.prepareCall("{call zadatak4(?)}");
			
			
			Scanner scanner = new Scanner(System.in);
			String storeName = scanner. nextLine();;
			
			statement.setString(1, storeName);
			
			statement.execute();
			
			resultSet =statement.getResultSet();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("Name") + " " + resultSet.getString("NumberOfOrders") + 
						" " + resultSet.getString("ProfitMade") + " " + resultSet.getString("AverageProducts"));
			}
			
			scanner.close();
			
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
