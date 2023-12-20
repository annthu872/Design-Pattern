package com.example.designpattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Notification {
	String message;
	
	public void setMessage(String msg) {
		message = msg;
	}
	
	public void alertDisplay() {
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.ERROR);
        
        a.getDialogPane().getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        
        a.setContentText(message);
        a.show();
	}

}
