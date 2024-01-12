package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class country {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer country_id;
	@NotNull
    private String country;
	@NotNull
    private Timestamp last_update;

    public country() {
    }

    public country(Integer country_id, String country, Timestamp last_update) {
        this.country_id = country_id;
        this.country = country;
        this.last_update = last_update;
	}

	public void setcountry_id(Integer country_id) {
		this.country_id = country_id;
	}
	public Integer getcountry_id() {
		return this.country_id;
	}

	public void setcountry(String country) {
		this.country = country;
	}
	public String getcountry() {
		return this.country;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}