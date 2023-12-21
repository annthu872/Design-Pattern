package com.example.tablehandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.designpattern.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.MouseEvent;

public class TableGenFromDB{
    @FXML
    private TableView<ObservableList<String>> myTable;    
    private String tableName = "";
    private ArrayList<String> data;
    private ArrayList<String> columnNames = new ArrayList<String>();
    private ObservableList<ObservableList<String>> tabledata = FXCollections.observableArrayList();
    
    public void setTableName(String table) {
    	tableName = table;
    	getData();
    }
    public String getTableName() {
    	return tableName;
    }
    public ArrayList<String> getFieldname() {
    	return data;
    }
    public ArrayList<String> getColumnNames() {
    	return columnNames;
    }
    public void setcolumnNames(ArrayList<String> col) {
    	columnNames = col;
    }
    public void setTableData(ObservableList<ObservableList<String>> dt) {
    	tabledata = dt;
    }
    public ObservableList<ObservableList<String>> getTableData() {
    	return tabledata;
    }
    public void addRow(ObservableList<String> newData) {
    	newData = FXCollections.observableArrayList();
    	myTable.getItems().add(newData);
    }
    
    private static TableGenFromDB instance;
    public static synchronized TableGenFromDB getInstance() {
        if (instance == null) {
            instance = new TableGenFromDB();
        }
        return instance;
    }
    
    protected TableGenFromDB() {}
    
    public void buildColumn(String colName) {
    	TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
    	col.setSortable(false);
    	int colIndex = columnNames.indexOf(colName);
    	col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));
    	myTable.getColumns().add(col);
    }
//     Public static ObservableList<COA> getAllCOA(){
    public void getData() {
        
        myTable.getItems().clear();
        myTable.getColumns().clear();
        
        for(String colName : columnNames) {
        	buildColumn(colName);
        }
        
        for (ObservableList<String> row : tabledata) {
            myTable.getItems().add(row);
        }
        
        myTable.setOnMouseClicked((MouseEvent event) -> {
            // Get the selected row and column index
            int rowIndex = myTable.getSelectionModel().getSelectedIndex();
            ObservableList<String> cellData = myTable.getItems().get(rowIndex);
            data = new ArrayList<>(cellData);
//                System.out.println("Selected Cell Data: " + cellData);
        });
    }
}
