package com.example.designpattern.notification;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InformationNotification implements NotificationInterface {

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		 Alert a = new Alert(AlertType.INFORMATION);
	     a.setContentText(message);
	     a.show();
	}

}
