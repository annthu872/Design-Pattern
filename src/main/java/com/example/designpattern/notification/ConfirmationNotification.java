package com.example.designpattern.notification;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConfirmationNotification implements NotificationInterface {

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		 Alert a = new Alert(AlertType.CONFIRMATION);
	     a.setContentText(message);
	     a.show();
	}

}
