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
		mScreen.getChildren().add(loader.load());
		TableGenFromDB table = loader.getController();
		table.setTableName("inventory");
		return mScreen;
    }
}
