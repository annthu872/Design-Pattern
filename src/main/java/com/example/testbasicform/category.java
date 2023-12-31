package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class category {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte category_id;
	@NotNull
    private String name;
	@NotNull
    private Timestamp last_update;

    public category() {
    }

    public category(Byte category_id, String name, Timestamp last_update) {
        this.category_id = category_id;
        this.name = name;
        this.last_update = last_update;
	}

	public void setcategory_id(Byte category_id) {
		this.category_id = category_id;
	}
	public Byte getcategory_id() {
		return this.category_id;
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