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
    public static Connection conn = DatabaseConnection.connection;
    @FXML
    private TableView<ObservableList<String>> myTable;
    private ObservableList<ObservableList> data = FXCollections.observableArrayList();
    
    private String tableName = "hs_hr_employee";
    
    public void setTableName(String table) {
    	tableName = table;
    	getData();
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
    
    private TableGenFromDB() {}
//     Public static ObservableList<COA> getAllCOA(){
    public void getData() {
        Statement st = null;
        ResultSet rs;
        
        myTable.getItems().clear();
        myTable.getColumns().clear();
        
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				st = conn.createStatement();
	            String recordQuery = ("SELECT * FROM " + tableName);
	            rs = st.executeQuery(recordQuery);
	            
	            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
	                //We are using non property style for making dynamic table
	            	final int j = i;
	            	TableColumn<ObservableList<String>, String> col = new TableColumn<>(rs.getMetaData().getColumnName(i + 1));
	            	col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j)));
	            	col.setSortable(false);
	                myTable.getColumns().add(col);
	            }
	            
	            while(rs.next()){
	            	ObservableList<String> row = FXCollections.observableArrayList();
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row.add(rs.getString(i));
	                }
	                data.add(row);
	                myTable.getItems().addAll(row);
	            }

	            for (ObservableList<?> outerList : data) {
	                for (Object item : outerList) {
	                    System.out.print(item + " ");
	                }
	                System.out.println();  // Move to the next line after printing each inner list
	            }     

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
            myTable.setOnMouseClicked((MouseEvent event) -> {
                // Get the selected row and column index
                int rowIndex = myTable.getSelectionModel().getSelectedIndex();
                ObservableList<String> cellData = myTable.getItems().get(rowIndex);
                ArrayList<String> data = new ArrayList<>(cellData);
                System.out.println("Selected Cell Data: " + cellData);
            });
    }
}
