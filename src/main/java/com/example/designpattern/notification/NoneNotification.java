package com.example.designpattern.notification;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NoneNotification implements NotificationInterface {

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		Alert a = new Alert(AlertType.NONE);
	     a.setContentText(message);
	     a.show();
	}
}
