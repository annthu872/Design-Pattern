package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class rental {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rental_id;
	@NotNull
    private String rental_date;
	@NotNull
    private String inventory_id;
	@NotNull
    private Integer customer_id;
	@NotNull
    private String return_date;
	@NotNull
    private Byte staff_id;
	@NotNull
    private Timestamp last_update;

    public rental() {
    }

    public rental(String rental_id, String rental_date, String inventory_id, Integer customer_id, String return_date, Byte staff_id, Timestamp last_update) {
        this.rental_id = rental_id;
        this.rental_date = rental_date;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.return_date = return_date;
        this.staff_id = staff_id;
        this.last_update = last_update;
	}

	public void setrental_id(String rental_id) {
		this.rental_id = rental_id;
	}
	public String getrental_id() {
		return this.rental_id;
	}

	public void setrental_date(String rental_date) {
		this.rental_date = rental_date;
	}
	public String getrental_date() {
		return this.rental_date;
	}

	public void setinventory_id(String inventory_id) {
		this.inventory_id = inventory_id;
	}
	public String getinventory_id() {
		return this.inventory_id;
	}

	public void setcustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getcustomer_id() {
		return this.customer_id;
	}

	public void setreturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getreturn_date() {
		return this.return_date;
	}

	public void setstaff_id(Byte staff_id) {
		this.staff_id = staff_id;
	}
	public Byte getstaff_id() {
		return this.staff_id;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}