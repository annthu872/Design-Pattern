package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class inventory {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String inventory_id;
	@NotNull
    private Integer film_id;
	@NotNull
    private Byte store_id;
	@NotNull
    private Timestamp last_update;

    public inventory() {
    }

    public inventory(String inventory_id, Integer film_id, Byte store_id, Timestamp last_update) {
        this.inventory_id = inventory_id;
        this.film_id = film_id;
        this.store_id = store_id;
        this.last_update = last_update;
	}

	public void setinventory_id(String inventory_id) {
		this.inventory_id = inventory_id;
	}
	public String getinventory_id() {
		return this.inventory_id;
	}

	public void setfilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	public Integer getfilm_id() {
		return this.film_id;
	}

	public void setstore_id(Byte store_id) {
		this.store_id = store_id;
	}
	public Byte getstore_id() {
		return this.store_id;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}