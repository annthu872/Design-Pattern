package com.example.testbasicform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.designpattern.DatabaseConnection;
import com.example.tablehandler.TableController;
import com.example.tablehandler.TableUIHandle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class BaseForm<T> {
	Connection conn = DatabaseConnection.connection;
	
	//TableController tableInstance = TableController.getInstance();
	
	private ArrayList<String> columnNames = new ArrayList<String>();
	private ObservableList<ObservableList<String>> tableData = FXCollections.observableArrayList();
	String tableName = "";
	private ArrayList<T> data;
	
    private Class<T> clazz;

    public BaseForm(Class<T> clazz) {
        this.clazz = clazz;
		getAllAttributes(this.clazz);
		setTableName(this.clazz);
    }
	
	public void setArrayList(ArrayList<T> dt) {
		data = dt;
		setTableData(convertObjectListToObservableList(dt));
	}
    public void setTableName(Class<?> clazz) {
        this.tableName = clazz.getSimpleName();
    }
	
    public void setColumnNames(ArrayList<String> col) {
    	columnNames = col;
    }
    
    public void setTableData(ObservableList<ObservableList<String>> dt) {
    	tableData = dt;
    }
    
    public String getTableName() {
    	return tableName;
    }
    
    public ObservableList<ObservableList<String>> getTableData(){
    	return tableData;
    }
    
    public ArrayList<String> getColumnNames() {
    	return columnNames;
    }
    
    public void getAllAttributes(Class<?> clazz) {
        ArrayList<String> attributeNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            attributeNames.add(field.getName());
        }

        columnNames = attributeNames;
    }
    
    public ObservableList<ObservableList<String>> convertObjectListToObservableList(ArrayList<?> objectList) {
        ObservableList<ObservableList<String>> tableData = FXCollections.observableArrayList();

        for (Object object : objectList) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (String columnName : columnNames) {
                try {
                    Field field = object.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    Object value = field.get(object);
                    row.add(String.valueOf(value));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            tableData.add(row);
        }

        return tableData;
    }
    
    public void execute(String sql) {
        try {
            Statement stmt = conn.createStatement();
            int affectedRows = stmt.executeUpdate(sql);
            System.out.println("Affected rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public void add(String addClause) {
		StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
		for (String columnName : columnNames) {
            sql.append(columnName).append(", ");
        }
        sql.setLength(sql.length() - 2); // Remove the last comma
        sql.append(") VALUES ");
        sql.append(addClause);
        System.out.println(sql.toString());
        //execute(sql.toString());
	}
    
	public void delete(ArrayList<String> rowData) {
	    StringBuilder conditionBuilder = new StringBuilder();
	    for (int i = 0; i < columnNames.size(); i++) {
	        String columnName = columnNames.get(i);
	        String value = rowData.get(i);

	        if (i > 0) {
	            conditionBuilder.append(" AND ");
	        }

	        try {
	            Field field = clazz.getDeclaredField(columnName);
	            Class<?> fieldType = field.getType();

	            // Assuming the values need to be treated differently based on their types
	            if (fieldType == String.class) {
	                conditionBuilder.append(columnName).append(" = '").append(value).append("'");
	            } else if (fieldType == int.class || fieldType == boolean.class || fieldType == double.class) {
	                conditionBuilder.append(columnName).append(" = ").append(value);
	            } else {
	                conditionBuilder.append(columnName).append(" = '").append(value).append("'");
	            }
	        } catch (NoSuchFieldException e) {
	            e.printStackTrace();
	        }
	    }
	    String condition = conditionBuilder.toString();
	    String sql = "DELETE FROM " + tableName + " WHERE " + condition;
        System.out.println(sql);
		execute(sql);
	}
	
	public void update(String setClause, String condition) {
		String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE " + condition;
		//execute(sql.toString());
	}
	
	public T parseResultSetToObject(ResultSet rs) throws SQLException {
        try {
            // Create an instance of the class T
            T object = clazz.getDeclaredConstructor().newInstance();

            // Iterate through the columns and set values in the object
            for (String columnName : columnNames) {
                try {
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    Object value = rs.getObject(columnName);
                    field.set(object, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return object;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
	
    public ArrayList<T> parseResultSetToObjectList(ResultSet rs) {
        ArrayList<T> objectList = new ArrayList<>();
        try {
            while (rs.next()) {
                T object = parseResultSetToObject(rs);
                objectList.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }
    
    public ArrayList<T> executeQueryAndParse(String sql) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return parseResultSetToObjectList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void read(TableController tableInstance) {
        String sql = "SELECT * FROM " + tableName;
        data = executeQueryAndParse(sql);
        setTableData(convertObjectListToObservableList(data));
        tableInstance.setForm(this);
        //tableInstance.setcolumnNames(this.getColumnNames());
		//tableInstance.setTableData(this.getTableData());
		//tableInstance.updateData();
        //tableInstance.setForm(this);
    }

}
