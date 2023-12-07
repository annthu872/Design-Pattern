package com.example.designpattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class DatabaseConnection {
	static String url = "jdbc:mysql://localhost:3306/mydatabase";
	static String user = "root";
	static String password = "";
    static Connection connection;
	public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
		try {
			connection = DriverManager.getConnection(url, user, password);
			if (connection != null) {
                System.out.println("Connected to the database!");

            } else {
                System.out.println("Failed to make connection!");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        }
	public List<String> getSchemaList() {
		List<String> schemaList = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = connection.getMetaData().getCatalogs();
			 while (rs.next()) {
//	            System.out.println("TABLE_CAT = " + rs.getString("TABLE_CAT") );
	            schemaList.add(rs.getString("TABLE_CAT"));
	        }
	        return schemaList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        ResultSet rs = stmt.executeQuery("select * from employees");
//        // show data
//        while (rs.next()) {
//            System.out.println(rs.getInt(1) + "  " + rs.getString(2) 
//                    + "  " + rs.getString(3)+ "  " + rs.getString(4));
//        }
        return null;
	}
	public void close() {
		 try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	}
}
