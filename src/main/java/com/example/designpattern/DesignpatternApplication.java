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
import table.Table;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import column.Column;
import database.DatabaseConnector;

@SpringBootApplication


public class DesignpatternApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/screen/SignIn.fxml"));
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("/css/application.css").toExternalForm();
			scene.getStylesheets().add(css);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "root";
		
		DatabaseConnector db = new DatabaseConnector(url, user, password, "advanced_web_programming_website");
		db.connect();
		
		//get all table data
		List<Table> data = new ArrayList<>();
		List<String> tableNames = db.getTableList("advanced_web_programming_website");

	    if (tableNames != null) {
	        for (String tableName : tableNames) {
	            Table table = new Table(tableName, db);
	            table.fetchData();
	            data.add(table);
	        }
	    }
		
		db.close();
	}
}
