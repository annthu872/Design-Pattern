package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class language {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte language_id;
	@NotNull
    private String name;
	@NotNull
    private Timestamp last_update;

    public language() {
    }

    public language(Byte language_id, String name, Timestamp last_update) {
        this.language_id = language_id;
        this.name = name;
        this.last_update = last_update;
	}

	public void setlanguage_id(Byte language_id) {
		this.language_id = language_id;
	}
	public Byte getlanguage_id() {
		return this.language_id;
	}

	public void setname(String name) {
		this.name = name;
	}
	public String getname() {
		return this.name;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}