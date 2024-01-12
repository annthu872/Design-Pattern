package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class customer {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;
	@NotNull
    private Byte store_id;
	@NotNull
    private String first_name;
	@NotNull
    private String last_name;
	@NotNull
    private String email;
	@NotNull
    private Integer address_id;
	@NotNull
    private Boolean active;
	@NotNull
    private String create_date;
	@NotNull
    private Timestamp last_update;

    public customer() {
    }

    public customer(Integer customer_id, Byte store_id, String first_name, String last_name, String email, Integer address_id, Boolean active, String create_date, Timestamp last_update) {
        this.customer_id = customer_id;
        this.store_id = store_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address_id = address_id;
        this.active = active;
        this.create_date = create_date;
        this.last_update = last_update;
	}

	public void setcustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getcustomer_id() {
		return this.customer_id;
	}

	public void setstore_id(Byte store_id) {
		this.store_id = store_id;
	}
	public Byte getstore_id() {
		return this.store_id;
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

	public void setemail(String email) {
		this.email = email;
	}
	public String getemail() {
		return this.email;
	}

	public void setaddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getaddress_id() {
		return this.address_id;
	}

	public void setactive(Boolean active) {
		this.active = active;
	}
	public Boolean getactive() {
		return this.active;
	}

	public void setcreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getcreate_date() {
		return this.create_date;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}