package com.example.designpattern;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignInTempController {
	@FXML
	private Button loginBtn;
	
	@FXML
	public void initialize() {
		loginBtn.setOnAction(e -> {
            System.out.println("Login button clicked!");
            NotificationController.display();
        });
	}
	
}
