package com.example.testbasicform;

public class PersonForm extends BaseForm<Person> {
	public PersonForm() {
		super();
		getAllAttributes(Person.class);
		setTableName(Person.class);
		// TODO Auto-generated constructor stub
	}
}
