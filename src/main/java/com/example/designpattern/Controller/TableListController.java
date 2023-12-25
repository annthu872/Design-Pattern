package com.example.designpattern.Controller;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import com.example.designpattern.decorator.IScreenUnit;
import com.example.tablehandler.TableGenFromDB;
import com.example.testbasicform.BaseForm;
import com.example.testbasicform.PersonForm;
import com.example.testbasicform.film_categoryForm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button; 
import javafx.scene.layout.AnchorPane;
public class TableListController implements Initializable {

    @FXML
    private VBox tablelistpane;
    
    private int choosenTablePos = 0;
    private List<String> tableList;
    public TableListController(List<String> tableList) {
        this.tableList = new ArrayList<>(tableList);
    }
    private List<Button> tableListButton = new ArrayList<Button>();
    private Object jobType;
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
                System.out.println("Button clicked! choosenPos:" + button.getText()+ ":");
                for( int j =0; j< tableListButton.size(); j++) {
                	tableListButton.get(j).getStyleClass().remove("table-button-choosen");
                	tableListButton.get(j).getStyleClass().add("table-button-not-choosen");
                }
                button.getStyleClass().add("table-button-choosen");
                
                String buttonText = button.getText();
                String formClassName = buttonText + "Form";

                try {
                    // Load the class
                    Class<?> formClass = Class.forName("com.example.testbasicform." + formClassName);

                    // Create an instance of the class
                    Constructor<?> constructor = formClass.getConstructor();
                    BaseForm<?> formInstance = (BaseForm<?>) constructor.newInstance();
                    formInstance.read();
                    // Now you can use the created formInstance as needed
                    // For example, call methods or perform actions related to the form

                } catch (ClassNotFoundException | NoSuchMethodException | SecurityException
                        | InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
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
