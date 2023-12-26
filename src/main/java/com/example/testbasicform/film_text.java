package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class film_text {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer film_id;
	@NotNull
    private String title;
	@NotNull
    private String description;

    public film_text() {
    }

    public film_text(Integer film_id, String title, String description) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
	}

	public void setfilm_id(Integer film_id) {
		this.film_id = film_id;
	}
	public Integer getfilm_id() {
		return this.film_id;
	}

	public void settitle(String title) {
		this.title = title;
	}
	public String gettitle() {
		return this.title;
	}

	public void setdescription(String description) {
		this.description = description;
	}
	public String getdescription() {
		return this.description;
	}

}