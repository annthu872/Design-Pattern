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
import com.example.designpattern.filegenerator.FileGenerator;
import com.example.designpattern.table.Table;
import com.example.tablehandler.TableGenFromDB;
import com.example.designpattern.Default.*;




public class DesignpatternApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseUserTableInDatabase.fxml"));
			ChooseUserTableController controller = new ChooseUserTableController();
			loader.setController(controller);
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
    private static Authentication auth = Authentication.getInstance();

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DesignpatternApplication.class, args);
		launch(args);

//		List<List<Object>> list = DatabaseConnection.getInstance().getColumnNamesAndTypes(SharedVariableHolder.database, "hi");
//		for (List<Object> pair : list) {
//            if (pair.size() == 2 && pair.get(0) instanceof String && pair.get(0).equals("username")) {
//                // Match found, return the datatype
//                try {
//					try {
//						System.out.println(auth.SqlTypetoJavaType((String) pair.get(1)));
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        }		
	}
	
}
