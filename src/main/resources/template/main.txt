package com.example.designpattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.IAuthentication;
import com.example.registry.ResetPasswordController;
import com.example.registry.ResetPasswordController2;
import com.example.registry.SignInController;
import com.example.registry.SignUpController;

import IoCContainer.IoCContainer;
import IoCContainer.ResetPassword1ControllerInterface;
import IoCContainer.ResetPassword2ControllerInterface;
import IoCContainer.SignInControllerInterface;
import IoCContainer.SignUpControllerInterface;

@SpringBootApplication
public class DesignpatternApplication extends Application {
	private final IoCContainer container = new IoCContainer();
	@Override
	public void start(Stage primaryStage) {
		try {
			container.register(SignInControllerInterface.class, SignInController.class);
			container.register(SignUpControllerInterface.class, SignUpController.class);
			container.register(ResetPassword1ControllerInterface.class, ResetPasswordController.class);
			container.register(ResetPassword2ControllerInterface.class, ResetPasswordController2.class);
			container.register(IAuthentication.class, Authentication.class);


	        SignInControllerInterface signInController = IoCContainer.resolve(SignInControllerInterface.class);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
            loader.setController(signInController);
            Parent root = loader.load();
            String css = this.getClass().getResource("/css/style.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		SpringApplication.run(DesignpatternApplication.class, args);
		launch(args);
	}
	
}
