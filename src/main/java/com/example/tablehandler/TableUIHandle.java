package com.example.tablehandler;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class TableUIHandle {
    @FXML
    private TableView<ObservableList<String>> myTable;    
    private ArrayList<String> rowData;
    private ArrayList<String> columnNames = new ArrayList<String>();
    private ObservableList<ObservableList<String>> tabledata = FXCollections.observableArrayList();
    
    public ArrayList<String> getFieldname() {
    	return rowData;
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
    
    private static TableUIHandle instance;
    public static synchronized TableUIHandle getInstance() {
        if (instance == null) {
            instance = new TableUIHandle();
        }
        return instance;
    }
    
    protected TableUIHandle() {}
    
    public void buildColumn(String colName) {
    	TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
    	col.setSortable(false);
    	int colIndex = columnNames.indexOf(colName);
    	col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));
    	myTable.getColumns().add(col);
    }
//     Public static ObservableList<COA> getAllCOA(){
    public void updateData() {
        
        myTable.getItems().clear();
        myTable.getColumns().clear();
        
        for(String colName : columnNames) {
        	buildColumn(colName);
        }
        
        for (ObservableList<String> row : tabledata) {
            myTable.getItems().add(row);
        }
        
        
        
        if(tabledata!=null) {
            myTable.setOnMouseClicked((MouseEvent event) -> {
                // Get the selected row and column index
                int rowIndex = myTable.getSelectionModel().getSelectedIndex();
                ObservableList<String> cellData = myTable.getItems().get(rowIndex);
                rowData = new ArrayList<>(cellData);
                System.out.println(rowData);
//                    System.out.println("Selected Cell Data: " + cellData);
            });
        }

    }
}
