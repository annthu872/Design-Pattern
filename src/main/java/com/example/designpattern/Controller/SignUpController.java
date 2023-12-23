package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.designpattern.Default.Authentication;
import com.example.designpattern.decorator.HeadingUIUnit;
import com.example.designpattern.decorator.TableDetailButton;
import com.example.designpattern.decorator.TableDetailUIUnit;
import com.example.designpattern.decorator.TableListUIUnit;

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

public class SignUpController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtAnswer;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtQuestion;

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
			//Handle login check
			System.out.println("username:"+txtUsername.getText());
			System.out.println("txtPassword:"+txtPassword.getText());
			System.out.println("txtQuestion:"+txtQuestion.getText());
			System.out.println("txtAnswer:"+txtAnswer.getText());
			
			Authentication auth = Authentication.getInstance();
			if(!auth.checkSignUpAccountExist(txtUsername.getText(), txtPassword.getText())) {
				if(auth.createAccount(txtUsername.getText(), txtPassword.getText()))
				try {
					//chuyển screen
					
//					Parent root = new HeadingUIUnit(new TableListUIUnit( new TableDetailButton(new TableDetailUIUnit()))).getUI();
//					Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
//					Scene scene = new Scene(root);
//					String css = this.getClass().getResource("/css/style.css").toExternalForm();
//					scene.getStylesheets().add(css);
//					stage.setScene(scene);
					Parent root;
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
					SignInController controller = new SignInController();
					loader.setController(controller);
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
				else {
					//Notification: tạo tài khoản bị lỗi
					System.out.println("Create Account Error");
					
				}
			}
			else {
				//Notification: tạo tài khoản đã tồn tại
				System.out.println("Username already exist, please choose another username");
			}

		});		
	}

}
