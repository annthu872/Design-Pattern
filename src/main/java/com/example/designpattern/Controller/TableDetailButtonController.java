package com.example.designpattern.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.tablehandler.TableGenFromDB;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.*;
public class TableDetailButtonController implements Initializable{

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		List<String> dataList = TableGenFromDB.getInstance().getFieldname();
		
//		btnAdd.setOnAction(e -> PopupWindow.display(TableGenFromDB.getInstance().getColumnNames()));
//		btnEdit.setOnAction(e -> PopupWindow.display(TableGenFromDB.getInstance().getColumnNames(),TableGenFromDB.getInstance().getFieldname()));
	}
}
