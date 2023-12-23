package com.example.designpattern.decorator;

import java.io.IOException;

import com.example.designpattern.Controller.TableDetailButtonController;
import com.example.tablehandler.TableUIHandle;
import com.example.testbasicform.film_textForm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class TableDetail implements IScreenUnit {
	
	@Override
    public Parent getUI() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/BaseUI.fxml"));
		AnchorPane a = new AnchorPane();
		TableUIHandle table = TableUIHandle.getInstance();
		TableDetailButtonController controller = new TableDetailButtonController();
		film_textForm f = new film_textForm();
		f.read();
		controller.setForm(f);
		loader.setController(table);
		loader.setController(controller);
		mScreen.getChildren().add(loader.load());
		

		/*table.setcolumnNames(f.getColumnNames());
		table.setTableData(f.getTableData());
		table.getData();*/
		return mScreen;
    }
}
