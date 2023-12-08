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
	    DatabaseConnection dbConnect = new DatabaseConnection();
	    dbConnect.connect();

	    // Get schema list
	    List<String> schemaNames = dbConnect.getSchemaList();
	    System.out.println("Schema Names: " + schemaNames);

	    // Get table list of the first database
	    if (schemaNames != null && !schemaNames.isEmpty()) {
	        String firstDatabase = schemaNames.get(0);
	        List<String> tableNames = dbConnect.getTableList(firstDatabase);
	        System.out.println("Tables in " + firstDatabase + ": " + tableNames);

	        // Get column names and data types of the specified table
	        if (tableNames != null && !tableNames.isEmpty()) {
	            String firstTable = "users"; // Specify the table name here
	            List<List<Object>> columnInfo = dbConnect.getColumnNamesAndTypes(firstDatabase, firstTable);
	            List<List<Object>> columnData = dbConnect.getColumnData(firstDatabase, firstTable);

	            // Printing column names and data types
	            System.out.println("Column Names and Data Types for table " + firstTable + ":");
	            if (columnInfo != null) {
	                for (List<Object> column : columnInfo) {
	                    System.out.println("Column Name: " + column.get(0) + ", Data Type: " + column.get(1));
	                }
	            } else {
	                System.out.println("No column information found for " + firstTable);
	            }

	            // Printing data rows
	            System.out.println("\nData from " + firstTable + ":");
	            if (columnData != null) {
	                for (List<Object> row : columnData) {
	                    System.out.println(row);
	                }
	            } else {
	                System.out.println("No data found for " + firstTable);
	            }
	        } else {
	            System.out.println("No tables found in " + firstDatabase);
	        }
	    } else {
	        System.out.println("No schemas found");
	    }

	    dbConnect.close();
	}


}
