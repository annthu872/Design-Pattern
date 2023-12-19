package com.example.designpattern.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private Button btnDelete;
    private Button btnEdit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> tableList = new ArrayList<>();
		tableList.add("Cao Nguyên");
		tableList.add("Nguyễn Duy");
		tableList.add("TRương toàn thịnh");
		
		
		btnAdd.setOnAction(e -> AddPopupWindow.display(tableList));
	}
}
