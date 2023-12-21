package com.example.designpattern.decorator;

import java.io.IOException;

import com.example.tablehandler.TableGenFromDB;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
public class TableDetailUIUnit implements IScreenUnit {
	
	@Override
    public Parent getUI() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableUI.fxml"));
		AnchorPane a = new AnchorPane();
		TableGenFromDB table = TableGenFromDB.getInstance();
		loader.setController(table);
		mScreen.getChildren().add(loader.load());

//		table.setTableName("hs_hr_employee");
		return mScreen;
    }
}
