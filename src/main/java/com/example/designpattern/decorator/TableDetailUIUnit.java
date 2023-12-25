package com.example.designpattern.decorator;
import com.example.testbasicform.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.tablehandler.TableController;
import com.example.tablehandler.TableUIHandle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
public class TableDetailUIUnit implements IScreenUnit {
	
	@Override
    public Parent getUI() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableUI.fxml"));
		AnchorPane a = new AnchorPane();
		TableUIHandle table = TableUIHandle.getInstance();
		loader.setController(table);
		mScreen.getChildren().add(loader.load());
		
		film_textForm f = new film_textForm();
		f.read(TableController.getInstance());
		return mScreen;
    }
}
