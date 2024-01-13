package IoCContainer;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;

import javafx.fxml.Initializable;

public interface ResetPassword1ControllerInterface extends Initializable {
	public void initialize(URL location, ResourceBundle resources);
	public void setSignInController(SignInControllerInterface signInController);
	public void setResetPassword2Controller(ResetPassword2ControllerInterface rsPassController);
	public void setAuthentication(IAuthentication auth);
}