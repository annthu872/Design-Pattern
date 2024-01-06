package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.IAuthentication;
import com.example.designpattern.authenticationScreenInterface.ResetPassword1ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.ResetPassword2ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.SignInControllerInterface;
import com.example.designpattern.notification.InformationNotification;
import com.example.designpattern.notification.Notification;
import com.example.designpattern.notification.WarningNotification;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResetPasswordController2 implements ResetPassword2ControllerInterface {


    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtAnswer;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordConfirm;

    @FXML
    private TextField txtUsername;

    String username = "";
    private IAuthentication auth;
    private SignInControllerInterface signInController;
    
    public void setAuthentication(IAuthentication auth) {
    	this.auth = auth;
    }
    @Override
    public void setUsername(String username) {
    	this.username = username;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.txtUsername.setText(username);
    	this.txtAnswer.setPromptText(auth.loadQuestionResetPassword(username));
		btnBack.setOnAction(e->{
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
				loader.setController(signInController);
				root = loader.load();
//				root = FXMLLoader.load(getClass().getResource("/screen/SignIn.fxml"));
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
		btnConfirm.setOnAction(e->{
			System.out.println("username:"+txtUsername.getText());
			System.out.println("txtAnswer:"+txtAnswer.getText());
			System.out.println("txtPassword:"+txtPassword.getText());
			System.out.println("txtPasswordConfirm:"+txtPasswordConfirm.getText());
			Notification noti = new Notification();
			if(this.txtPassword.getText().equals("")) {
				noti.setMessage("Password can not null");
				noti.setNotiType(new WarningNotification());
				noti.display();
				return;
			}
			if(!this.txtPassword.getText().equals( this.txtPasswordConfirm.getText())) {
				noti.setMessage("Confirm Password not match Password");
				noti.setNotiType(new WarningNotification());
				noti.display();
				return;
			}
			if(!auth.checkResetPasswordAnswerCorrect(this.txtUsername.getText(), this.txtAnswer.getText())) {
				noti.setMessage("Answer for Reset Password Question is not Correct");
				noti.setNotiType(new WarningNotification());
				noti.display();
				return;
			}
			if(auth.resetPassword(this.txtUsername.getText(), this.txtPassword.getText())) {
				noti.setMessage("Reset Pasword Complete, let's get back to Sign In Page to try your new Password");
				noti.setNotiType(new InformationNotification());
				noti.display();
				Parent root;
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
					loader.setController(signInController);
					root = loader.load();
//					root = FXMLLoader.load(getClass().getResource("/screen/SignIn.fxml"));
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
		});				
	}
	@Override
	public void setSignInController(SignInControllerInterface signInController) {
		// TODO Auto-generated method stub
		this.signInController = signInController;
	}

}
