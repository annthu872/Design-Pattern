package com.example.testbasicform;

public class employeesForm extends BaseForm<employees> {
	public employeesForm() {
		super();
		getAllAttributes(employees.class);
		setTableName(employees.class);
		// TODO Auto-generated constructor stub
	}
}
