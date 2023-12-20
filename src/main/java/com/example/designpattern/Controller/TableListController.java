package com.example.designpattern.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button; 
public class TableListController implements Initializable {

    @FXML
    private VBox tablelistpane;
    
    private int choosenTablePos = 0;
    private List<String> tableList;
    public TableListController(List<String> tableList) {
        this.tableList = new ArrayList<>(tableList);
    }
    private List<Button> tableListButton = new ArrayList<Button>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for ( int i =0; i< tableList.size(); i++) {
			Button button = new Button();
			button.setText(tableList.get(i));
			button.getStyleClass().add("table-button");
			if( i != choosenTablePos)
				button.getStyleClass().add("table-button-not-choosen");
			else 
				button.getStyleClass().add("table-button-choosen");
			tableListButton.add(button);
			tablelistpane.getChildren().add(button);
			button.setOnAction(event -> {				
				choosenTablePos = tableList.indexOf(button.getText()); // Update choosenPos when the button is clicked
                System.out.println("Button clicked! choosenPos: " + choosenTablePos);
                for( int j =0; j< tableListButton.size(); j++) {
                	tableListButton.get(j).getStyleClass().remove("table-button-choosen");
                	tableListButton.get(j).getStyleClass().add("table-button-not-choosen");

                }
                button.getStyleClass().add("table-button-choosen");
            });
		}
	}
	
//	public List<String> getTables(){
//		List<String> tableList = new ArrayList<>();
//		tableList.add("Cao Nguyên");
//		tableList.add("Nguyễn Duy");
//		tableList.add("TRương toàn thịnh");
//		tableList.add("Thư");
//		tableList.add("Thảo");
//		tableList.add("Cao Nguyên 1");
//		tableList.add("Nguyễn Duy 2");
//		tableList.add("TRương toàn thịnh 3");
//		tableList.add("Thư 4");
//		tableList.add("Thảo 5");
//		tableList.add("Cao Nguyên 2");
//		tableList.add("Nguyễn Duy 12");
//		tableList.add("TRương toàn thịnh TRương toàn thịnh");
//		tableList.add("Thư 1231");
//		tableList.add("Thảo 532");
//
//		return tableList;
//	}

}
