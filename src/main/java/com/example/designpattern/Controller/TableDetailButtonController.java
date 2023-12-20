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

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> colList = new ArrayList<>();
		colList.add("Cao Nguyên");
		colList.add("Nguyễn Duy");
		colList.add("TRương toàn thịnh");
		List<String> dataList = new ArrayList<>();
		dataList.add("Cao Nguyên");
		dataList.add("Nguyễn Duy");
		dataList.add("TRương toàn thịnh");
		
		btnAdd.setOnAction(e -> PopupWindow.display(colList));
		btnEdit.setOnAction(e -> PopupWindow.display(colList,dataList));


	}
}
