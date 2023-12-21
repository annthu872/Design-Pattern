package com.example.baseform;

import com.example.designpattern.Controller.PopupWindow;
import com.example.tablehandler.TableGenFromDB;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class BaseForm<T> {
	private T data;
	

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private TableView<?> myTable;
    
    public BaseForm(T data) {
        this.data = data;
    }
    
    private void createTable() {
    	 myTable.getColumns().clear();
    }
	
	void add() {
		btnAdd.setOnAction(e -> PopupWindow.display(TableGenFromDB.getInstance().getColumnNames()));
	};
	void update() {
		btnEdit.setOnAction(e -> PopupWindow.display(TableGenFromDB.getInstance().getColumnNames(),TableGenFromDB.getInstance().getFieldname()));
	};
	void delete() {};
}
