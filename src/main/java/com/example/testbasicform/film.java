package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class film {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer film_id;
	@NotNull
    private String title;
	@NotNull
    private String description;
	@NotNull
    private String release_year;
	@NotNull
    private Byte language_id;
	@NotNull
    private Byte original_language_id;
	@NotNull
    private Byte rental_duration;
	@NotNull
    private String rental_rate;
	@NotNull
    private Integer length;
	@NotNull
    private String replacement_cost;
	@NotNull
    private String rating;
	@NotNull
    private String special_features;
	@NotNull
    private Timestamp last_update;

    public film() {
    }

    public film(Integer film_id, String title, String description, String release_year, Byte language_id, Byte original_language_id, Byte rental_duration, String rental_rate, Integer length, String replacement_cost, String rating, String special_features, Timestamp last_update) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.language_id = language_id;
        this.original_language_id = original_language_id;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.special_features = special_features;
        this.last_update = last_update;
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

	public void setrelease_year(String release_year) {
		this.release_year = release_year;
	}
	public String getrelease_year() {
		return this.release_year;
	}

	public void setlanguage_id(Byte language_id) {
		this.language_id = language_id;
	}
	public Byte getlanguage_id() {
		return this.language_id;
	}

	public void setoriginal_language_id(Byte original_language_id) {
		this.original_language_id = original_language_id;
	}
	public Byte getoriginal_language_id() {
		return this.original_language_id;
	}

	public void setrental_duration(Byte rental_duration) {
		this.rental_duration = rental_duration;
	}
	public Byte getrental_duration() {
		return this.rental_duration;
	}

	public void setrental_rate(String rental_rate) {
		this.rental_rate = rental_rate;
	}
	public String getrental_rate() {
		return this.rental_rate;
	}

	public void setlength(Integer length) {
		this.length = length;
	}
	public Integer getlength() {
		return this.length;
	}

	public void setreplacement_cost(String replacement_cost) {
		this.replacement_cost = replacement_cost;
	}
	public String getreplacement_cost() {
		return this.replacement_cost;
	}

	public void setrating(String rating) {
		this.rating = rating;
	}
	public String getrating() {
		return this.rating;
	}

	public void setspecial_features(String special_features) {
		this.special_features = special_features;
	}
	public String getspecial_features() {
		return this.special_features;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}