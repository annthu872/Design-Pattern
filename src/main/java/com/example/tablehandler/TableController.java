package com.example.tablehandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.baseform.BaseForm;
import com.example.designpattern.Controller.PopupWindow;
import com.example.designpattern.notification.ErrorNotification;
import com.example.designpattern.notification.InformationNotification;
import com.example.designpattern.notification.Notification;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    public ArrayList<String> getFieldname() {
    	return rowData;
    }
    
    public void addRow(ArrayList<String> newData) {
        ObservableList<String> newRow = FXCollections.observableArrayList(newData);
        currentForm.getTableData().add(newRow);
    }
    
    public void editRow(ArrayList<String> newData) {
        ObservableList<String> selectedRowData = myTable.getSelectionModel().getSelectedItem();

        if (selectedRowData != null) {
            int index = myTable.getItems().indexOf(selectedRowData);
            ObservableList<String> rowToEdit = FXCollections.observableArrayList(newData);
            myTable.getItems().set(index, rowToEdit);
        } else {
            // Handle case when no row is selected
            System.out.println("No row selected for editing.");
        }
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
        
        myTable.setItems(currentForm.getTableData());
        
        if(currentForm.getTableData()!=null) {
            myTable.setOnMouseClicked((MouseEvent event) -> {
                int rowIndex = myTable.getSelectionModel().getSelectedIndex();
                ObservableList<String> cellData = myTable.getItems().get(rowIndex);
                rowData = new ArrayList<>(cellData);
            });
        }
        
        
        ContextMenu menu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Add");
        MenuItem menuItem2 = new MenuItem("Update");
        MenuItem menuItem3 = new MenuItem("Delete");
        MenuItem menuItem4 = new MenuItem("Refresh");
        menu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4);
        
        menuItem1.setOnAction(e -> {
            PopupWindow.display(currentForm);
        });
        
        menuItem2.setOnAction(e ->{
        	if(currentForm.getTableData()!=null) {
        		PopupWindow.display(currentForm,this.getFieldname());
        	}
        });
        
        menuItem3.setOnAction(e ->{
        	ObservableList<String> selectedItem = myTable.getSelectionModel().getSelectedItem();
        	if (selectedItem != null) {
    			boolean res = currentForm.delete(rowData);
    			if(res) {
    				currentForm.getTableData().remove(selectedItem);
    			}
    			else {
    				Notification noti = new Notification();
    		        noti.setMessage("There might be some error, we can't delete this row.");
    		        noti.setNotiType(new ErrorNotification());
    		        noti.display();
    			}
        	}
        });
        
        menuItem4.setOnAction(e->{
        	currentForm.read(instance);
        });
        
        myTable.setRowFactory(tv -> {
            TableRow<ObservableList<String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ObservableList<String> rData = row.getItem();
                    rowData = new ArrayList<>(rData);
                    System.out.println(rowData);
                    PopupWindow.display(currentForm,this.getFieldname());
                }
                
                
            row.setOnContextMenuRequested(revent -> {
		        	if (menu.isShowing()) {
		                menu.hide();
		            }
                    

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
		
		btnAdd.setOnAction(e -> PopupWindow.display(currentForm));
		btnEdit.setOnAction(e -> PopupWindow.display(currentForm,this.getFieldname()));
		btnDelete.setOnAction(e ->{
			boolean res = currentForm.delete(rowData);
			if(res) {
				ObservableList<String> selectedItem = myTable.getSelectionModel().getSelectedItem();
				currentForm.getTableData().remove(selectedItem);
			}
			else {
				Notification noti = new Notification();
		        noti.setMessage("There might be some error, we can't delete this row.");
		        noti.setNotiType(new ErrorNotification());
		        noti.display();
			}
		});
	}
}
