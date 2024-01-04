package com.example.designpattern.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.filegenerator.FileGenerator;
import com.example.designpattern.table.Table;

public class ChooseGeneratedProjectLocationController {
	@FXML
    private TextField txtLocation;
	
	@FXML
    private Button btnChoose; 
	
    @FXML
    private Button btnConfirm; 

    @FXML
    private void initialize() {
    	txtLocation.setEditable(false);
    	btnConfirm.setOnAction(e -> {
    		confirmAction();
    	});
    }

    @FXML
    private void chooseFileLocation() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Project Location");

        Stage stage = (Stage) btnConfirm.getScene().getWindow();
        fileChooser.setInitialFileName("NewProject");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            String selectedFilePath = selectedFile.getAbsolutePath();
            txtLocation.setText(selectedFilePath);
            System.out.println("Selected location: " + selectedFilePath);
        } else {
            System.out.println("File selection canceled.");
        }
    }

    private void confirmAction() {
        String selectedLocation = txtLocation.getText();
        if (!selectedLocation.isEmpty()) {
        	DatabaseConnection con = DatabaseConnection.getInstance();
            List<Table> tables = con.getTablesWithColumns();
            FileGenerator gen = new FileGenerator(
            		System.getProperty("user.dir"), 
            		selectedLocation);
            gen.generateAll(tables);
            con.close();
        } else {
            System.out.println("Please select a file location.");

        }
    }
}
