package com.example.designpattern.authenticationScreenInterface;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;

public interface ResetPassword2ControllerInterface {
	public void initialize(URL location, ResourceBundle resources);
	public void setUsername(String username);
	public void setSignInController(SignInControllerInterface signInController);
	public void setAuthentication(IAuthentication auth);
}
