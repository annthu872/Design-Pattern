package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

		});		
	}

}
