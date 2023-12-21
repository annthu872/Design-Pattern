package com.example.testbasicform;

import java.util.ArrayList;

public class PersonForm extends BaseForm {
	public PersonForm() {
		super();
		getAllAttributes(Person.class);
		setTableName(Person.class);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Person> data;
	public void setArrayList(ArrayList<Person> dt) {
		data = dt;
		setTableData(convertObjectListToObservableList(dt));
	}
}
