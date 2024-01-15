package com.example.designpattern.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.notification.ErrorNotification;
import com.example.designpattern.notification.Notification;

public class DatabaseConnectionController {

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnConfirm;

    // JavaFX properties for data binding
    private StringProperty urlProperty = new SimpleStringProperty();
    private StringProperty  usernameProperty = new SimpleStringProperty();
    private StringProperty  passwordProperty = new SimpleStringProperty();

    @FXML
    private void initialize() {
        // Bind text fields to properties
        txtUrl.textProperty().bindBidirectional(urlProperty);
        txtUsername.textProperty().bindBidirectional(usernameProperty);
        txtPassword.textProperty().bindBidirectional(passwordProperty);
        
        urlProperty.set(SharedVariableHolder.url);

        btnConfirm.setOnAction(e -> {
        	if (urlProperty.get() != null)
        		SharedVariableHolder.url = urlProperty.get();
        	if (usernameProperty.get() != null)
        		SharedVariableHolder.user = usernameProperty.get();
            if (passwordProperty.get() != null)
            	SharedVariableHolder.password = passwordProperty.get();
            if (!checkConnection()) alertConnection();
     		try {
     			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseDatabase.fxml"));
     			ChooseDatabaseController controller = new ChooseDatabaseController();
     			loader.setController(controller);
     			Parent root = loader.load();
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
    private boolean checkConnection() {
    	return DatabaseConnection.getInstance().isConnect();
    }
    
    @FXML
    private void alertConnection() {
    	Notification noti = new Notification();
        noti.setMessage("Problem when connect to database!");
        noti.setNotiType(new ErrorNotification());
        noti.display();
    }
}
