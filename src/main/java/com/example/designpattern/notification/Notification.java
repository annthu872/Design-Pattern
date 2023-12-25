package com.example.designpattern.notification;

public class Notification {
	String message = "";
	NotificationInterface notiType;
	
	public void setMessage(String msg) {
		message = msg;
	}
	
	public void setNotiType(NotificationInterface type) {
		notiType = type;
	}
	
	public void display() {
		notiType.display(message);
	}
}
