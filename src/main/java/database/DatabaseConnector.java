package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseConnector {
    private String url;
    private String user;
    private String password;
    private String databaseName;
    static Connection connection;

    public DatabaseConnector(String url, String user, String password, String databaseName) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

	public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
		try {
			connection = DriverManager.getConnection(url + databaseName, user, password);
			if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}        
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
	
	public List<String> getSchemaList() {
		List<String> schemaList = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = connection.getMetaData().getCatalogs();
			 while (rs.next()) {
		        schemaList.add(rs.getString("TABLE_CAT"));
		    }
		    return schemaList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCurrentDatabase() {
		return databaseName;
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
	
	public List<List<Object>> getColumnDetails(String databaseName, String tableName) {
	    List<List<Object>> columnInfo = new ArrayList<>();
	    try {
	        DatabaseMetaData metaData = connection.getMetaData();
	        ResultSet columns = metaData.getColumns(databaseName, null, tableName, null);
	        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);

	        Set<String> primaryKeysSet = new HashSet<>();
	        while (primaryKeys.next()) {
	            primaryKeysSet.add(primaryKeys.getString("COLUMN_NAME"));
	        }

	        while (columns.next()) {
	            List<Object> columnData = new ArrayList<>();
	            String columnName = columns.getString("COLUMN_NAME");
	            String dataType = columns.getString("TYPE_NAME");
	            boolean allowNull = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
	            boolean isPrimaryKey = primaryKeysSet.contains(columnName);

	            columnData.add(columnName);
	            columnData.add(dataType);
	            columnData.add(allowNull ? "Allows Null" : "Doesn't Allow Null");
	            columnData.add(isPrimaryKey ? "Primary Key" : "Not Primary Key");

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
