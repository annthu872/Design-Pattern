package com.example.testbasicform;
import com.example.designpattern.*;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class employees {
	private int id ;
	private String name ;
	int age ;
	private String department;
	public employees(int id, String name, int age, String department) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.department = department;

	}
}
