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
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.designpattern.Controller.*;
import com.example.designpattern.decorator.*;
import com.example.designpattern.decorator.TableDetailButton;
import com.example.tablehandler.TableGenFromDB;
import com.example.designpattern.Default.*;
@SpringBootApplication


public class DesignpatternApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			DatabaseConnection con = new DatabaseConnection();
			con.connect();
			
			Parent root = new HeadingUIUnit(new TableListUIUnit( new TableDetailButton(new TableDetailUIUnit()))).getUI();
			String css = this.getClass().getResource("/css/style.css").toExternalForm();
			Scene scene = new Scene(root);
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
//		SpringApplication.run(DesignpatternApplication.class, args);
//		launch(args);
		CreateDefaultTable tableCreator = new CreateDefaultTable();

		// Create a table for User class
		try {
			tableCreator.createTableToDatabaseWithTableName("hi",UserA.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
