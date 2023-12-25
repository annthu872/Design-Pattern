package com.example.tablehandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.designpattern.Controller.PopupWindow;
import com.example.testbasicform.BaseForm;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TableController implements Initializable {
	
    private BaseForm<?> currentForm;
    public BaseForm<?> getForm(){
    	return currentForm;
    }
    public void setForm(BaseForm<?> form) {
        this.currentForm = form;
        System.out.println(currentForm.getClass());
        updateData();
    }

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;
    
    @FXML
    private TableView<ObservableList<String>> myTable;    
    private ArrayList<String> rowData;
    //private ArrayList<String> columnNames = new ArrayList<String>();
    //private ObservableList<ObservableList<String>> tabledata = FXCollections.observableArrayList();
    
    public ArrayList<String> getFieldname() {
    	return rowData;
    }
   /* public ArrayList<String> getColumnNames() {
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
    }*/
    public void addRow(ObservableList<String> newData) {
    	newData = FXCollections.observableArrayList();
    	myTable.getItems().add(newData);
    }
    
    private static TableController instance;
    public static synchronized TableController getInstance() {
        if (instance == null) {
            instance = new TableController();
        }
        return instance;
    }
    
    protected TableController() {}
    
    public void buildColumn(String colName) {
    	TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
    	col.setSortable(false);
    	int colIndex = currentForm.getColumnNames().indexOf(colName);
    	col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));
    	myTable.getColumns().add(col);
    }
//     Public static ObservableList<COA> getAllCOA(){
    public void updateData() {
        
        myTable.getItems().clear();
        myTable.getColumns().clear();
        
        for(String colName : currentForm.getColumnNames()) {
        	buildColumn(colName);
        }
        
        for (ObservableList<String> row : currentForm.getTableData()) {
            myTable.getItems().add(row);
        }
        
        if(currentForm.getTableData()!=null) {
            myTable.setOnMouseClicked((MouseEvent event) -> {
                int rowIndex = myTable.getSelectionModel().getSelectedIndex();
                ObservableList<String> cellData = myTable.getItems().get(rowIndex);
                rowData = new ArrayList<>(cellData);
            });
        }
        
        myTable.setRowFactory(tv -> {
            TableRow<ObservableList<String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ObservableList<String> rData = row.getItem();
                    rowData = new ArrayList<>(rData);
                    System.out.println(rowData);
                    PopupWindow.display(currentForm.getColumnNames(),this.getFieldname());
                }
                
            row.setOnContextMenuRequested(revent -> {
                    ContextMenu menu = new ContextMenu();
                    MenuItem menuItem1 = new MenuItem("Add");
                    MenuItem menuItem2 = new MenuItem("Update");
                    MenuItem menuItem3 = new MenuItem("Delete");
                    menu.getItems().addAll(menuItem1, menuItem2, menuItem3);
                    
                    menuItem1.setOnAction(e -> {
                        PopupWindow.display(currentForm.getColumnNames());
                    });
                    
                    menuItem2.setOnAction(e ->{
                    	PopupWindow.display(currentForm.getColumnNames(),this.getFieldname());
                    });
                    
                    menuItem3.setOnAction(e ->{
                    	ObservableList<String> selectedItem = myTable.getSelectionModel().getSelectedItem();
            			myTable.getItems().remove(selectedItem);
            			currentForm.delete(rowData);	
                    });

                    if (menu != null) {
                        System.out.println("Hello");
                        menu.show(myTable, event.getScreenX(), event.getScreenY());
                    }
                });
            });
            return row;
        });
        
        

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//		List<String> dataList = TableGenFromDB.getInstance().getFieldname();
		
		btnAdd.setOnAction(e -> PopupWindow.display(currentForm.getColumnNames()));
		btnEdit.setOnAction(e -> PopupWindow.display(currentForm.getColumnNames(),this.getFieldname()));
		btnDelete.setOnAction(e ->{
			ObservableList<String> selectedItem = myTable.getSelectionModel().getSelectedItem();
			myTable.getItems().remove(selectedItem);
			currentForm.getTableData().remove(selectedItem);
			currentForm.delete(rowData);	
		});
	}
}
