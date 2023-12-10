package com.example.designpattern.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button; 
public class TableListController implements Initializable {

    @FXML
    private VBox tablelistpane;
    
    private int choosenTablePos = 0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> tableList = new ArrayList<>(getTables());
		for ( int i =0; i< tableList.size(); i++) {
			Button button = new Button();
			button.setText(tableList.get(i));
			button.getStyleClass().add("table-button");
			if( i != choosenTablePos)
				button.getStyleClass().add("table-button-not-choosen");
			else 
				button.getStyleClass().add("table-button-choosen");
			
			tablelistpane.getChildren().add(button);
		}
	}
	public List<String> getTables(){
		List<String> tableList = new ArrayList<>();
		tableList.add("Cao Nguyên");
		tableList.add("Nguyễn Duy");
		tableList.add("TRương toàn thịnh");
		tableList.add("Thư");
		tableList.add("Thảo");
		tableList.add("Cao Nguyên");
		tableList.add("Nguyễn Duy");
		tableList.add("TRương toàn thịnh");
		tableList.add("Thư");
		tableList.add("Thảo");
		tableList.add("Cao Nguyên");
		tableList.add("Nguyễn Duy");
		tableList.add("TRương toàn thịnh TRương toàn thịnh");
		tableList.add("Thư");
		tableList.add("Thảo");

		return tableList;
	}

}
