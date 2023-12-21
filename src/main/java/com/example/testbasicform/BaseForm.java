package com.example.testbasicform;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.designpattern.DatabaseConnection;
import com.example.tablehandler.TableGenFromDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class BaseForm {
	private ArrayList<String> columnNames = new ArrayList<String>();
	private ObservableList<ObservableList<String>> tableData = FXCollections.observableArrayList();
	Connection conn = DatabaseConnection.connection;
	String tableName = "";
	
    public void setTableName(Class<?> clazz) {
        this.tableName = clazz.getSimpleName();
    }
	
	public BaseForm(){
		
	}
	
    public void setColumnNames(ArrayList<String> col) {
    	columnNames = col;
    }
    
    public void setTableData(ObservableList<ObservableList<String>> dt) {
    	tableData = dt;
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
			ResultSet rs = stmt.executeQuery(sql);
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
    
	public void delete(String condition) {
		String sql = "DELETE FROM " + tableName + " WHERE " + condition;
		//execute(sql);
	}
	
	public void update(String setClause, String condition) {
		String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE " + condition;
		//execute(sql.toString());
	}
	
}
