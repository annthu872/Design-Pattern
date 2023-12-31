package com.example.designpattern;

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
import com.example.designpattern.filegenerator.FileGenerator;
import com.example.designpattern.table.Table;
import com.example.tablehandler.TableGenFromDB;

@SpringBootApplication


public class DesignpatternApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {

			DatabaseConnection con = DatabaseConnection.getInstance();
			
			Parent root = new HeadingUIUnit(new TableListUIUnit( new TableUIUnit())).getUI();
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
		
		/*DatabaseConnection con = DatabaseConnection.getInstance();
        List<Table> tables = con.getTablesWithColumns();
        FileGenerator gen = new FileGenerator("C:\\Users\\duong\\OneDrive\\Máy tính\\New folder");
        gen.generateAll(System.getProperty("user.dir"), tables);
        con.close();*/
	}
}
