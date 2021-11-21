package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Procedura11 {

    public static void main(String[] args) throws ClassNotFoundException{

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        	 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventureworks", "root", "root");

            statement = connection.prepareCall("{call zadatak11(?)}");


            Scanner scanner = new Scanner(System.in);
            System.out.println("Unesite ime jezika:");
            String storeName = scanner. nextLine();;

            statement.setString(1, storeName);

            statement.execute();

            resultSet =statement.getResultSet();

            if (!resultSet.next()) {
            	System.out.println("Ne postoji upustvo za zeljeni jezik.");
            	return;
            }
            while(resultSet.next()) {
                System.out.println(resultSet.getString("productID") + " " + resultSet.getString("Name") +
                        " " + resultSet.getString("listprice") + " " + resultSet.getString("Description")+" "+ resultSet.getString("lng"));
            }
 

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