package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.IAuthentication;
import com.example.designpattern.authenticationScreenInterface.IoCContainer;
import com.example.designpattern.authenticationScreenInterface.ResetPassword1ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.SignInControllerInterface;
import com.example.designpattern.authenticationScreenInterface.SignUpControllerInterface;
import com.example.designpattern.decorator.*;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignInController implements SignInControllerInterface {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button buttonForgotPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    private IAuthentication auth;

    
    private SignUpControllerInterface signUpController;
    private ResetPassword1ControllerInterface rsPassController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		buttonForgotPassword.setOnAction(e->{
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ResetPassword.fxml"));
                loader.setControllerFactory(new CustomControllerFactory());
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
		});
		btnSignUp.setOnAction(e->{
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignUp.fxml"));
				System.out.println(signUpController.getClass());
				loader.setController(signUpController);
				SignUpControllerInterface latestSignUpController = IoCContainer.resolve(SignUpControllerInterface.class);
                loader.setController(latestSignUpController);
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
		btnLogin.setOnAction(e->{
			//Handle login check
			System.out.println("username:"+txtUsername.getText());
			System.out.println("txtPassword:"+txtPassword.getText());
			Notification noti = new Notification();

			if(auth.checkSignIn(txtUsername.getText(), txtPassword.getText())) {
				try {
					//chuyển screen
					noti.setMessage("Sign In Successful");
					noti.setNotiType(new InformationNotification());
					noti.display();
					
					Parent root = new HeadingUIUnit(new TableListUIUnit( new TableUIUnit())).getUI();
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
				//Notification
				noti.setMessage("Sign In Failure, Please check your username and password");
				noti.setNotiType(new WarningNotification());
				noti.display();		
				}
			
		});
	}

	@Override
	public void setSignUpController(SignUpControllerInterface signUpController) {
		// TODO Auto-generated method stub
		this.signUpController = signUpController;
	}

	@Override
	public void setResetPassController(ResetPassword1ControllerInterface rsPassController) {
		// TODO Auto-generated method stub
		this.rsPassController = rsPassController;
	}
	@Override
	public void setAuthentication(IAuthentication auth) {
    	this.auth = auth;
    }
}

