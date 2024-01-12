package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class film_category {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer film_id;
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte category_id;
	@NotNull
    private Timestamp last_update;

    public film_category() {
    }

    public film_category(Integer film_id, Byte category_id, Timestamp last_update) {
        this.film_id = film_id;
        this.category_id = category_id;
        this.last_update = last_update;
	}

	public void setfilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	public Integer getfilm_id() {
		return this.film_id;
	}

	public void setcategory_id(Byte category_id) {
		this.category_id = category_id;
	}
	public Byte getcategory_id() {
		return this.category_id;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}