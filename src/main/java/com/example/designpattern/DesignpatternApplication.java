package com.example.designpattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.designpattern.Controller.*;
import com.example.designpattern.decorator.*;
import com.example.designpattern.decorator.TableDetailButton;
import com.example.designpattern.table.Table;
import com.example.tablehandler.TableGenFromDB;

import com.example.designpattern.Default.*;

import com.example.testbasicform.Actor;
import com.example.testbasicform.ActorForm;

@SpringBootApplication


public class DesignpatternApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {

			DatabaseConnection con = DatabaseConnection.getInstance();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
			SignInController controller = new SignInController();
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("/css/style.css").toExternalForm();
			scene.getStylesheets().add(css);

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
//		SpringApplication.run(DesignpatternApplication.class, args);
//		launch(args);
		
		Authentication.createTableToDatabase()
	}
}
