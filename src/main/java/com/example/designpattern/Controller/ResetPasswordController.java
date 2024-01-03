package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.Authentication;
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

public class ResetPasswordController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnContinue;

    @FXML
    private TextField txtUsername;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnBack.setOnAction(e->{
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
				SignInController controller = new SignInController();
				loader.setController(controller);
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
		btnContinue.setOnAction(e->{
			//Handle login check
			System.out.println("username:"+txtUsername.getText());
			if(Authentication.getInstance().checkUsernameExist(txtUsername.getText())) {
				Parent root;
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ResetPassword2.fxml"));
					ResetPasswordController2 controller = new ResetPasswordController2(txtUsername.getText());
					loader.setController(controller);
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
			else {
				Notification noti = new Notification();
				noti.setMessage("Username is not Existed");
				noti.setNotiType(new WarningNotification());
				noti.display();
			}
		});				
	}

}
