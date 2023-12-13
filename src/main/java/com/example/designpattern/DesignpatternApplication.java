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
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.designpattern.Controller.*;
import com.example.designpattern.decorator.*;
@SpringBootApplication


public class DesignpatternApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			List<String> tableList = new ArrayList<>();
//			tableList.add("Cao Nguyên");
//			tableList.add("Nguyễn Duy");
//			tableList.add("TRương toàn thịnh");
//			tableList.add("Thư");
//			tableList.add("Thảo");
//			tableList.add("Cao Nguyên 1");
//			tableList.add("Nguyễn Duy 2");
//			tableList.add("TRương toàn thịnh 3");
//			tableList.add("Thư 4");
//			tableList.add("Thảo 5");
//			tableList.add("Cao Nguyên 2");
//			tableList.add("Nguyễn Duy 12");
//			tableList.add("TRương toàn thịnh TRương toàn thịnh");
//			tableList.add("Thư 1231");
//			tableList.add("Thảo 532");
//			TableListController controller = new TableListController(tableList);
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableList.fxml"));
//			loader.setController(controller);
			Parent root = new HeadingUIUnit(new TableListUIUnit(new TableDetailUIUnit())).getUI();
			String css = this.getClass().getResource("/css/style.css").toExternalForm();
			Scene scene = new Scene(root);
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
