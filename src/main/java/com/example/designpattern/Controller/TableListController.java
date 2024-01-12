package com.example.designpattern.Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.baseform.BaseForm;
import com.example.designpattern.SharedVariableHolder;
import com.example.tablehandler.TableController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label; 

public class TableListController implements Initializable {

    @FXML
    private VBox tablelistpane;
    @FXML
    private Label txtSchemaName;
    private int choosenTablePos = 0;
    private List<String> tableList;
    public TableListController(List<String> tableList) {
        this.tableList = new ArrayList<>(tableList);
    }
    private List<Button> tableListButton = new ArrayList<Button>();
    private Object jobType;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        txtSchemaName.setText(SharedVariableHolder.database);
		try {
            // Load the class
			String temp = tableList.get(0);
            String formClassNameTemp = temp + "Form";
            Class<?> formClass = Class.forName("com.example.userform." + formClassNameTemp);

            // Create an instance of the class
            Constructor<?> constructor = formClass.getConstructor();
            BaseForm<?> formInstance = (BaseForm<?>) constructor.newInstance();
            formInstance.read(TableController.getInstance());
            System.out.println("it work");
            // Now you can use the created formInstance as needed
            // For example, call methods or perform actions related to the form

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
		for ( int i =0; i< tableList.size(); i++) {
			Button button = new Button();
			button.setText(tableList.get(i));
			button.getStyleClass().add("table-button");
			if( i != choosenTablePos) {
				button.getStyleClass().add("table-button-not-choosen");

			}
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
                    Class<?> formClass = Class.forName("com.example.userform." + formClassName);

                    // Create an instance of the class
                    Constructor<?> constructor = formClass.getConstructor();
                    BaseForm<?> formInstance = (BaseForm<?>) constructor.newInstance();
                    formInstance.read(TableController.getInstance());
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
	
}
