package com.example.designpattern.decorator;
import com.example.testbasicform.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.tablehandler.TableGenFromDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
public class TableDetailUIUnit implements IScreenUnit {
	
	@Override
    public Parent getUI() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableUI.fxml"));
		AnchorPane a = new AnchorPane();
		TableGenFromDB table = TableGenFromDB.getInstance();
		loader.setController(table);
		mScreen.getChildren().add(loader.load());
		
		List<employees> employeesList = new ArrayList<>();
		DatabaseConnection conn = new DatabaseConnection();
		
		conn.connect();
		Statement stmt;
		try {
			stmt = conn.connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + SharedVariableHolder.database +"."+"employees");
	        ResultSetMetaData metaData = rs.getMetaData();

	         int columnCount = metaData.getColumnCount();
	         while (rs.next()) {
	             int id = rs.getInt("id"); // Assuming "id" is the column name for employee ID
	             String name = rs.getString("name");
	             int age = rs.getInt("age");
	             String department = rs.getString("department");
	             employees employee = new employees(id, name, age, department);
	             employeesList.add(employee);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        employeesForm ef = new employeesForm();
		ef.setArrayList((ArrayList<employees>) employeesList);
		ObservableList<ObservableList<String>> tableData = ef.getTableData();
		table.setcolumnNames(ef.getColumnNames());
		table.setTableData(tableData);

		table.getData();

	    /*ArrayList<String> cars = new ArrayList<String>();
	    cars.add("Volvo");
	    cars.add("BMW");
	    cars.add("Ford");
	    ObservableList<String> row = FXCollections.observableArrayList();
	    row.add("abc");
	    row.add("def");
	    row.add("xyz");
	    ObservableList<ObservableList> data = FXCollections.observableArrayList();
	    data.add(row);
	    
	    table.setcolumnNames(cars);
	    table.getData();*/
		return mScreen;
    }
}
