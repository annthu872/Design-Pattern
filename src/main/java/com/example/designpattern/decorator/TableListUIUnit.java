package com.example.designpattern.decorator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		List<String> tableList = new ArrayList<>();
		tableList.add("Cao Nguyên");
		tableList.add("Nguyễn Duy");
		tableList.add("TRương toàn thịnh");
		tableList.add("Thư");
		tableList.add("Thảo");
		tableList.add("Cao Nguyên 1");
		tableList.add("Nguyễn Duy 2");
		tableList.add("TRương toàn thịnh 3");
		tableList.add("Thư 4");
		tableList.add("Thảo 5");
		tableList.add("Cao Nguyên 2");
		tableList.add("Nguyễn Duy 12");
		tableList.add("TRương toàn thịnh TRương toàn thịnh");
		tableList.add("Thư 1231");
		tableList.add("Thảo 532");
		TableListController controller = new TableListController(tableList);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/TableList.fxml"));
		loader.setController(controller);
		AnchorPane a = new AnchorPane();
		a.getChildren().add(loader.load());
		return a;
	}

}

