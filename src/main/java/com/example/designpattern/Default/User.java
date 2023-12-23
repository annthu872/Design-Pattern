package com.example.designpattern.Default;

public class User {
	private String username;
    private String password;
    private Boolean active;
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSQLAddClause() {
		// TODO Auto-generated method stub
		return "(" + this.username + " , " +this.password +" ) ";
	}
}
