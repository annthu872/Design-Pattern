package com.example.baseform;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class BaseForm<T> {
	private T data;
	
	@FXML
	private TableView myTable;
	
    public BaseForm(T data) {
        this.data = data;
    }
    
    private void createTable() {
    	 myTable.getColumns().clear();
    }
	
	void add() {};
	void update() {};
	void delete() {};
}
