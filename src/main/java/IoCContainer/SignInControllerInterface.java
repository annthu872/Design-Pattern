package IoCContainer;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;

import javafx.fxml.Initializable;

public interface SignInControllerInterface extends Initializable  {
	void setSignUpController(SignUpControllerInterface signUpController);
	void setResetPassController(ResetPassword1ControllerInterface rsPassController);
	public void initialize(URL location, ResourceBundle resources);
	public void setAuthentication(IAuthentication auth);
}
