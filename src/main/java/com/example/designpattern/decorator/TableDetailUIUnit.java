package com.example.designpattern.decorator;

import java.io.IOException;
import java.util.ArrayList;

import com.example.tablehandler.TableGenFromDB;
import com.example.testbasicform.Actor;
import com.example.testbasicform.ActorForm;

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
		TableGenFromDB table = TableGenFromDB.getInstance();
		loader.setController(table);
		mScreen.getChildren().add(loader.load());
		
		ArrayList<Actor> actlist = new ArrayList<Actor>();
		Actor a1 = new Actor(3, "Charley", "Lala");
		actlist.add(a1);
		
		ActorForm act = new ActorForm();
		act.setArrayList(actlist);
		ObservableList<ObservableList<String>> tableData = act.getTableData();
		table.setcolumnNames(act.getColumnNames());
		table.setTableData(tableData);

		table.getData();

	    /*ArrayList<String> cars = new ArrayList<String>();
	    cars.add("Volvo");
	    cars.add("BMW");
	    cars.add("Ford");
	    ObservableList<String> row = FXCollections.observableArrayList();
	    row.add("abc");
	    row.add("def");
	    row.add("xyz");
	    ObservableList<ObservableList> data = FXCollections.observableArrayList();
	    data.add(row);
	    
	    table.setcolumnNames(cars);
	    table.getData();*/
		return mScreen;
    }
}
