package com.example.baseform;

import java.util.ArrayList;

import com.example.designpattern.Controller.PopupWindow;
import com.example.tablehandler.TableGenFromDB;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.lang.reflect.Field;

public class BaseForm<T> {
	private T data;
	

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private TableView<T> myTable;
    
    public BaseForm(T data) {
        this.data = data;
    }
    
    protected void createTable(ArrayList<Column> colList) {
    	 myTable.getColumns().clear();
    	 Class<?> clazz = data.getClass();

         for (Field field : clazz.getDeclaredFields()) {
             TableColumn<T, Object> columnTable = new TableColumn<>(field.getName());
             columnTable.setCellValueFactory(cellData -> {
                 try {
                     field.setAccessible(true);
                     Object value = field.get(cellData.getValue());
                     return javafx.beans.binding.Bindings.createObjectBinding(() -> value);
                 } catch (IllegalAccessException e) {
                     e.printStackTrace();
                     return null;
                 }
             });

             myTable.getColumns().add(columnTable);
         }
    }
	
	void add() {
		btnAdd.setOnAction(e -> PopupWindow.display(TableGenFromDB.getInstance().getColumnNames()));
	};
	void update() {
		btnEdit.setOnAction(e -> PopupWindow.display(TableGenFromDB.getInstance().getColumnNames(),TableGenFromDB.getInstance().getFieldname()));
	};
	void delete() {};
}
