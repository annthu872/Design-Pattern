package com.example.testbasicform;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ActorForm extends BaseForm {
	public ActorForm() {
		super();
		getAllAttributes(Actor.class);
		setTableName(Actor.class);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Actor> data;
	public void setArrayList(ArrayList<Actor> dt) {
		data = dt;
		setTableData(convertObjectListToObservableList(dt));
	}

}
