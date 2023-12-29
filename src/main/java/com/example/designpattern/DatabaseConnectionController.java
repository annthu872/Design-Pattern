package com.example.designpattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.List;

public class DatabaseConnectionController {

    @FXML
    private ComboBox<String> databaseComboBox;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnConfirm;

    @FXML
    private void initialize() {
        DatabaseConnection con = DatabaseConnection.getInstance();
        List<String> schemaList = con.getSchemaList();
        databaseComboBox.getItems().addAll(schemaList);
        
        btnConfirm.setOnAction(e -> {
        	handleConfirmButtonClick();
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
