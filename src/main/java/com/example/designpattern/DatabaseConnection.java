package com.example.designpattern;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;

import com.example.designpattern.column.ColumnBuilder;
import com.example.designpattern.table.Table;
import com.example.designpattern.table.TableBuilder;

import java.util.ArrayList;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	public static Connection connection;
	private Thread connectionThread;
	
	private DatabaseConnection() {
		initializeConnectionThread();
	}
	
	public static DatabaseConnection getInstance() {
	    if (instance == null) {
	        synchronized (DatabaseConnection.class) {
	            if (instance == null) {
	                instance = new DatabaseConnection();
	                instance.connect();
	            }
	        }
	    }
	    return instance;
	}
	
	private void initializeConnectionThread() {
	    connectionThread = new Thread(() -> {});
	    connectionThread.start();
	}
	
	public void ensureConnection() {
        if (connection == null) {
            connect();
        }
    }

	private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
		try {
			connection = DriverManager.getConnection(SharedVariableHolder.url + SharedVariableHolder.database , SharedVariableHolder.user, SharedVariableHolder.password);
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
		ensureConnection();
		List<String> schemaList = new ArrayList<>();
		try {
			ResultSet rs = connection.getMetaData().getCatalogs();
			 while (rs.next()) {
	            schemaList.add(rs.getString("TABLE_CAT"));
	        }
	        return schemaList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		ensureConnection();
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
		ensureConnection();
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
		ensureConnection();
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
	
	public List<Table> getTablesWithColumns() {
		ensureConnection();
		List<Table> tableList = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tablesResultSet = metaData.getTables(SharedVariableHolder.database, null, "%", new String[]{"TABLE"});
            
            while (tablesResultSet.next()) {
                TableBuilder tableBuilder = new TableBuilder();
                String tableName = tablesResultSet.getString("TABLE_NAME");
                tableBuilder.setTableName(tableName);
                
                ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName);
                List<String> primaryKeys = new ArrayList<>();
                while (primaryKeysResultSet.next()) {
                    primaryKeys.add(primaryKeysResultSet.getString("COLUMN_NAME"));
                }
                
                ResultSet columnsResultSet = metaData.getColumns(SharedVariableHolder.database, null, tableName, null);
                while (columnsResultSet.next()) {
                    ColumnBuilder columnBuilder = new ColumnBuilder()
                    		.setClassName(columnsResultSet.getString("TYPE_NAME"))
                    		.setColumnName(columnsResultSet.getString("COLUMN_NAME"))
                    		.setAutoIncrement("YES".equals(columnsResultSet.getString("IS_AUTOINCREMENT")))
                    		.setNullable("YES".equals(columnsResultSet.getString("IS_NULLABLE")))
                    		.setIsPrimaryKey(primaryKeys.contains(columnsResultSet.getString("COLUMN_NAME")));

                    tableBuilder.addColumn(columnBuilder.build());
                }

                tableList.add(tableBuilder.build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }
	
	public boolean addRowToTable(String tableName, List<String> data) {
		ensureConnection();
        try {
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
            queryBuilder.append(tableName).append(" VALUES (");

            for (int i = 0; i < data.size(); i++) {
                queryBuilder.append("?");
                if (i < data.size() - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");

            String query = queryBuilder.toString();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < data.size(); i++) {
                preparedStatement.setObject(i + 1, data.get(i));
            }

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
