package com.example.baseform;

import java.util.ArrayList;

public class Actor {
	int actor_id;
	String firstname;
	String lastname;
	ArrayList<Column> col; 
	
	public Actor(){
		actor_id = 0;
		firstname = "abc";
		lastname = "xyz";
	}
	
	public Actor(int id, String name1, String name2) {
		actor_id=id;
		firstname = name1;
		lastname = name2;
	}
	
    public ArrayList<Column> getCol() {
        return col;
    }

}
