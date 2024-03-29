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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.notification.ErrorNotification;
import com.example.designpattern.notification.Notification;

public class ChooseDatabaseController {

    @FXML
    private ComboBox<String> databaseCombobox;

    @FXML
    private Button btnConfirm;
    
    private StringProperty selectedDatabase = new SimpleStringProperty();

    @FXML
    private void initialize() {
    	databaseCombobox.valueProperty().bindBidirectional(selectedDatabase);
        List<String> schemaList = DatabaseConnection.getInstance().getSchemaList();
        databaseCombobox.getItems().addAll(schemaList);

        btnConfirm.setOnAction(e -> {
        	if (databaseCombobox.getValue() == null || databaseCombobox.getValue().isEmpty()) {
                alert();
                return;
            }
            SharedVariableHolder.database = databaseCombobox.getValue();
            DatabaseConnection.getInstance().close();
            DatabaseConnection newConnection = DatabaseConnection.getInstance();
     		try {
     			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseUserTableInDatabase.fxml"));
    			ChooseUserTableController controller = new ChooseUserTableController(new Authentication());
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
    private void alert() {
    	Notification noti = new Notification();
        noti.setMessage("Please choose database!");
        noti.setNotiType(new ErrorNotification());
        noti.display();
    }
}
