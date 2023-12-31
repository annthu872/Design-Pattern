package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class film_actor {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actor_id;
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer film_id;
	@NotNull
    private Timestamp last_update;

    public film_actor() {
    }

    public film_actor(Integer actor_id, Integer film_id, Timestamp last_update) {
        this.actor_id = actor_id;
        this.film_id = film_id;
        this.last_update = last_update;
	}

	public void setactor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}
	public Integer getactor_id() {
		return this.actor_id;
	}

	public void setfilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	public Integer getfilm_id() {
		return this.film_id;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}