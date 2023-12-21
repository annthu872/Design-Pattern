package com.example.baseform;

public class ActorForm extends BaseForm<Actor> {

	public ActorForm(Actor data) {
        super(data);
        createTable(data.getCol());
    }
	
}
