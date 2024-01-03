package com.example.designpattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.designpattern.Controller.ChooseUserTableController;
import com.example.designpattern.Default.IAuthentication;

public class DatabaseConnectionController {

    @FXML
    private ComboBox<String> databaseComboBox;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnConfirm;

	private IAuthentication auth;
    DatabaseConnectionController(IAuthentication auth){
    	this.auth = auth;
    }
    @FXML
    private void initialize() {
        DatabaseConnection con = DatabaseConnection.getInstance();
        List<String> schemaList = con.getSchemaList();
        databaseComboBox.getItems().addAll(schemaList);
        
        btnConfirm.setOnAction(e -> {
        	handleConfirmButtonClick();
        	 Parent root;
     		try {
     			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseUserTableInDatabase.fxml"));
    			ChooseUserTableController controller = new ChooseUserTableController(auth);
     			loader.setController(controller);
     			root = loader.load();
//     			root = FXMLLoader.load(getClass().getResource("/screen/SignIn.fxml"));
     			Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
     			Scene scene = new Scene(root);
     			String css = this.getClass().getResource("/css/style.css").toExternalForm();
     			scene.getStylesheets().add(css);
     			stage.setScene(scene);
     		} catch (IOException e1) {
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}
        });
    }

    @FXML
    private void handleConfirmButtonClick() {
        String selectedDatabase = databaseComboBox.getValue();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        SharedVariableHolder.database = selectedDatabase != null ? selectedDatabase : SharedVariableHolder.database;
        SharedVariableHolder.user = username;
        SharedVariableHolder.password = password;

        System.out.println(selectedDatabase + " " + username + " " + password);
       
    }
}
