package com.example.designpattern;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class DesignpatternApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(DesignpatternApplication.class, args);
//	}
//
//}
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.File;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


public class DesignpatternApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/screen/SignIn.fxml"));
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("/css/application.css").toExternalForm();
			scene.getStylesheets().add(css);
//			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(System.getProperty("java.runtime.version"));
		SpringApplication.run(DesignpatternApplication.class, args);
		launch(args);
	}
}
