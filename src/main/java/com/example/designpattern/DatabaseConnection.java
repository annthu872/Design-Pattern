package com.example.designpattern;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class DatabaseConnection {
	public static String url = "jdbc:mysql://localhost:3306/" + SharedVariableHolder.database;
	public static String user = "root";
	public static String password = "192002";
    public static Connection connection;
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
	        }	
	}
	public List<String> getTableList(String databaseName) {
	    List<String> tableList = new ArrayList<>();
	    try {
	        DatabaseMetaData metaData = connection.getMetaData();
	        ResultSet tables = metaData.getTables(databaseName, null, "%", new String[] { "TABLE" });
	        while (tables.next()) {
	            tableList.add(tables.getString("TABLE_NAME"));
	        }
	        return tableList;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public List<List<Object>> getColumnNamesAndTypes(String databaseName, String tableName) {
	    List<List<Object>> columnInfo = new ArrayList<>();
	    try {
	        DatabaseMetaData metaData = connection.getMetaData();
	        ResultSet columns = metaData.getColumns(databaseName, null, tableName, null);

	        while (columns.next()) {
	            List<Object> columnData = new ArrayList<>();
	            String columnName = columns.getString("COLUMN_NAME");
	            String dataType = columns.getString("TYPE_NAME");

	            columnData.add(columnName);
	            columnData.add(dataType);
	            columnInfo.add(columnData);
	        }
	        return columnInfo;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public List<List<Object>> getColumnData(String databaseName, String tableName) {
	    List<List<Object>> columnData = new ArrayList<>();
	    try {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
	        ResultSetMetaData metaData = rs.getMetaData();
	        
	        int columnCount = metaData.getColumnCount();
	        while (rs.next()) {
	            List<Object> rowData = new ArrayList<>();
	            for (int i = 1; i <= columnCount; i++) {
	                Object value = rs.getObject(i);
	                rowData.add(value);
	            }
	            columnData.add(rowData);
	        }
	        return columnData;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
