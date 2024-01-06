package com.example.designpattern.authenticationScreenInterface;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;

import javafx.fxml.Initializable;

public interface SignUpControllerInterface extends Initializable  {
	public void initialize(URL location, ResourceBundle resources);
	public void setSignInController(SignInControllerInterface signInController);
	public void setAuthentication(IAuthentication auth);
}
