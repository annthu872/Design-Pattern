package com.example.designpattern.authenticationScreenInterface;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;

public interface ResetPassword1ControllerInterface {
	public void initialize(URL location, ResourceBundle resources);
	public void setSignInController(SignInControllerInterface signInController);
	public void setResetPassword2Controller(ResetPassword2ControllerInterface rsPassController);
	public void setAuthentication(IAuthentication auth);
}
