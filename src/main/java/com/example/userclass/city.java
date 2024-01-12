package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class city {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer city_id;
	@NotNull
    private String city;
	@NotNull
    private Integer country_id;
	@NotNull
    private Timestamp last_update;

    public city() {
    }

    public city(Integer city_id, String city, Integer country_id, Timestamp last_update) {
        this.city_id = city_id;
        this.city = city;
        this.country_id = country_id;
        this.last_update = last_update;
	}

	public void setcity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public Integer getcity_id() {
		return this.city_id;
	}

	public void setcity(String city) {
		this.city = city;
	}
	public String getcity() {
		return this.city;
	}

	public void setcountry_id(Integer country_id) {
		this.country_id = country_id;
	}
	public Integer getcountry_id() {
		return this.country_id;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}