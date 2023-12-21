package com.example.testbasicform;

public class ActorForm extends BaseForm<Actor> {
	public ActorForm() {
		super();
		getAllAttributes(Actor.class);
		setTableName(Actor.class);
		// TODO Auto-generated constructor stub
	}
}
