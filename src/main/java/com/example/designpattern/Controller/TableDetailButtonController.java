package com.example.designpattern.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.*;
public class TableDetailButtonController implements Initializable{
	@FXML
    private Button btnAdd;
    private Button btnDelete;
    private Button btnEdit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnAdd.setOnAction(e -> PopupWindow.display("Hello, this is a popup!"));
	}
}
