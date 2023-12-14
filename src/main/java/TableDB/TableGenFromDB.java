package TableDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;

public class TableGenFromDB {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "192002";
    
    public static Connection conn = null;
    @FXML
    private TableView<ObservableList<String>> myTable;
    private ObservableList<ObservableList> data = FXCollections.observableArrayList();
    
    private String tableName = "actor";

    // Public static ObservableList<COA> getAllCOA(){
    public void getAllstudentInfo() {
        Statement st = null;
        ResultSet rs;

        
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				st = conn.createStatement();
	            String recordQuery = ("SELECT * FROM " + tableName);
	            rs = st.executeQuery(recordQuery);
	            
	            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
	                //We are using non property style for making dynamic table
	            	final int j = i;
	            	TableColumn<ObservableList<String>, String> col = new TableColumn<>(rs.getMetaData().getColumnName(i + 1));
	            	col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j)));

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

	            //FINALLY ADDED TO TableView
	            
	            

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        
    }
}
