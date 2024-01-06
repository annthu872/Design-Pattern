package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.IAuthentication;
import com.example.designpattern.authenticationScreenInterface.ResetPassword1ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.ResetPassword2ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.SignInControllerInterface;
import com.example.designpattern.notification.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResetPasswordController implements ResetPassword1ControllerInterface {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnContinue;

    @FXML
    private TextField txtUsername;
    IAuthentication auth;
    private SignInControllerInterface signInController;
    private ResetPassword2ControllerInterface rsPassController;
    @Override
    public void setAuthentication(IAuthentication auth) {
    	this.auth = auth;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnBack.setOnAction(e->{
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
				loader.setController(signInController);
				root = loader.load();
				Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
				Scene scene = new Scene(root);
				String css = this.getClass().getResource("/css/style.css").toExternalForm();
				scene.getStylesheets().add(css);
				stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		btnContinue.setOnAction(e->{
			//Handle login check
			System.out.println("username:"+txtUsername.getText());
			if(auth.checkUsernameExist(txtUsername.getText())) {
				Parent root;
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ResetPassword2.fxml"));
					rsPassController.setUsername(txtUsername.getText());
					loader.setController(rsPassController);
					root = loader.load();
					Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
					Scene scene = new Scene(root);
					String css = this.getClass().getResource("/css/style.css").toExternalForm();
					scene.getStylesheets().add(css);
					stage.setScene(scene);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				Notification noti = new Notification();
				noti.setMessage("Username is not Existed");
				noti.setNotiType(new WarningNotification());
				noti.display();
			}
		});				
	}
	@Override
	public void setSignInController(SignInControllerInterface signInController) {
		this.signInController = signInController;
		
	}
	@Override
	public void setResetPassword2Controller(ResetPassword2ControllerInterface rsPassController) {
		// TODO Auto-generated method stub
		this.rsPassController = rsPassController;
	}

}
