package com.example.designpattern.decorator;

import java.io.IOException;

import com.example.tablehandler.TableController;
import com.example.testbasicform.film_textForm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class TableUIUnit implements IScreenUnit {
	
	@Override
    public Parent getUI() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/BaseUI.fxml"));
		AnchorPane a = new AnchorPane();
		TableController table = TableController.getInstance();
		loader.setController(table);
		mScreen.getChildren().add(loader.load());
		/*film_textForm f = new film_textForm();
		f.read(table);
		table.setcolumnNames(f.getColumnNames());
		table.setTableData(f.getTableData());
		table.getData();*/
		return mScreen;
    }
}
