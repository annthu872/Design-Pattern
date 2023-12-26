package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class actor {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actor_id;
	@NotNull
    private String first_name;
	@NotNull
    private String last_name;
	@NotNull
    private Timestamp last_update;

    public actor() {
    }

    public actor(Integer actor_id, String first_name, String last_name, Timestamp last_update) {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
	}

	public void setactor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}
	public Integer getactor_id() {
		return this.actor_id;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getfirst_name() {
		return this.first_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getlast_name() {
		return this.last_name;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}