package IoCContainer;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;

import javafx.fxml.Initializable;

public interface ResetPassword2ControllerInterface extends Initializable {
	public void initialize(URL location, ResourceBundle resources);
	public void setUsername(String username);
	public void setSignInController(SignInControllerInterface signInController);
	public void setAuthentication(IAuthentication auth);
}
