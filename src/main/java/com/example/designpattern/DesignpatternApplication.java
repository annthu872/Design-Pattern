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
import java.util.List;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


public class DesignpatternApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/screen/TableList.fxml"));
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("/css/style.css").toExternalForm();
			scene.getStylesheets().add(css);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//	    DatabaseConnection dbConnect = new DatabaseConnection();
//	    dbConnect.connect();
//
//	    // Get schema list
//	    List<String> schemaNames = dbConnect.getSchemaList();
//	    System.out.println("Schema Names: " + schemaNames);
//
//	    // Get table list of the first database
//	    if (schemaNames != null && !schemaNames.isEmpty()) {
////	        String firstDatabase = schemaNames.get(1);
//	    	String firstDatabase = "mydatabase";
//	        List<String> tableNames = dbConnect.getTableList(firstDatabase);
//	        System.out.println("Tables in " + firstDatabase + ": " + tableNames);
//	        
//	    } else {
//	        System.out.println("No schemas found");
//	    }
//
//	    dbConnect.close();
//	}
		SpringApplication.run(DesignpatternApplication.class, args);
		launch(args);
	}
}
