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
import com.example.designpattern.decorator.TableDetailButton;
@SpringBootApplication


public class DesignpatternApplication extends Application {
	public static String database = "mydatabase";
	
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
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/FieldnameLine.fxml"));
//			loader.setController(controller);TableListUIUnit
//			Parent root = loader.load();
			
			Parent root = new HeadingUIUnit(new TableListUIUnit( new TableDetailButton(new TableDetailUIUnit()))).getUI();
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
		SpringApplication.run(DesignpatternApplication.class, args);
		launch(args);
	}
}
