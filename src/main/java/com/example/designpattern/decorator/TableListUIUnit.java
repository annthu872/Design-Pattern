package com.example.designpattern.decorator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Controller.TableListController;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


public class TableListUIUnit extends ScreenUnitDecorator {

	public TableListUIUnit(IScreenUnit screen) {
		super(screen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Parent getownUI() throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableList.fxml"));
	    DatabaseConnection dbConnect = new DatabaseConnection();
	    dbConnect.connect();

	    // Get schema list
	    String database = SharedVariableHolder.database;
        List<String> tableNames = dbConnect.getTableList(database);
        System.out.println("Tables in " + database + ": " + tableNames);

	    dbConnect.close();
		TableListController controller = new TableListController(tableNames);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableList.fxml"));
		loader.setController(controller);
		AnchorPane a = new AnchorPane();
		a.getChildren().add(loader.load());
		return a;
	}

}

