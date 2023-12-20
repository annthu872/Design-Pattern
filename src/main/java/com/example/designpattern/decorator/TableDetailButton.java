package com.example.designpattern.decorator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.designpattern.Controller.TableDetailButtonController;
import com.example.designpattern.Controller.TableListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class TableDetailButton extends ScreenUnitDecorator {

	public TableDetailButton(IScreenUnit screen) {
		super(screen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Parent getownUI() throws IOException {
		TableDetailButtonController controller = new TableDetailButtonController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableDetailButton.fxml"));
		loader.setController(controller);
		AnchorPane a = new AnchorPane();
		a.getChildren().add(loader.load());
		return a;
	}
}

