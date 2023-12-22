package com.example.designpattern.Default;

import java.lang.reflect.Field;
import com.example.designpattern.*;

import java.sql.SQLException;
import java.sql.Statement;
public class Authentication {
	private String tableName = "users";
	public void createTableToDatabase(Class<? extends User> userClass) throws SQLException {
		DatabaseConnection conn = new DatabaseConnection();
		
		conn.connect();
		Statement  stmt = conn.connection.createStatement();
		StringBuilder createTableStatement = new StringBuilder("CREATE TABLE IF NOT EXISTS users (");

        // Add common fields
        createTableStatement.append("id INT AUTO_INCREMENT PRIMARY KEY,");
        createTableStatement.append("username VARCHAR(255) NOT NULL,");
        createTableStatement.append("password VARCHAR(255) NOT NULL,");

        // Add fields specific to the given userClass
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
        	if(field.getName() == "username" ||field.getName() == "password") {
        		continue;
        	}
            createTableStatement.append(field.getName()).append(" ")
                    .append(javaTypeToSqlType(field.getType())).append(",");
        }

        createTableStatement.deleteCharAt(createTableStatement.length() - 1);
        createTableStatement.append(");");
        System.out.println(createTableStatement);

        stmt.execute(createTableStatement.toString());
	}
	public void createTableToDatabaseWithTableName(String tableName,Class<? extends User> userClass) throws SQLException {
		DatabaseConnection conn = new DatabaseConnection();
		conn.connect();
		this.tableName = tableName;

		Statement  stmt = null;
		try {
			 stmt = conn.connection.createStatement();
			 StringBuilder createTableStatement = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
				createTableStatement.append(tableName).append(" ( ");


		        // Add common fields
		        createTableStatement.append("id INT AUTO_INCREMENT PRIMARY KEY,");
		        createTableStatement.append("username VARCHAR(255) NOT NULL,");
		        createTableStatement.append("password VARCHAR(255) NOT NULL,");

		        // Add fields specific to the given userClass
		        Field[] fields = userClass.getDeclaredFields();
		        for (Field field : fields) {
		        	if(field.getName() == "username" ||field.getName() == "password") {
		        		continue;
		        	}
		        	System.out.println(field.getType().toString());
		            System.out.println(javaTypeToSqlType(field.getType()));

		            createTableStatement.append(field.getName()).append(" ")
		                    .append(javaTypeToSqlType(field.getType())).append(",");
		        }

		        // Remove the trailing comma
		        createTableStatement.deleteCharAt(createTableStatement.length() - 1);

		        // Close the CREATE TABLE statement
		        createTableStatement.append(");");

		        System.out.println(createTableStatement);
		        stmt.execute(createTableStatement.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String javaTypeToSqlType(Class<?> javaType) {
        switch (javaType.getName()) {
        case "java.lang.String":
            return "VARCHAR(255)";
        case "int":
            return "INT";
        case "java.lang.Boolean":
            return "BIT";
        case "float":
            return "FLOAT";
        case "double":
            return "DOUBLE";
        default:
            return "";
        }
    }
}
