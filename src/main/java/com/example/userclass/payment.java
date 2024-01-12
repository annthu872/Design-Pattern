package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class payment {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_id;
	@NotNull
    private Integer customer_id;
	@NotNull
    private Byte staff_id;
	@NotNull
    private String rental_id;
	@NotNull
    private String amount;
	@NotNull
    private String payment_date;
	@NotNull
    private Timestamp last_update;

    public payment() {
    }

    public payment(Integer payment_id, Integer customer_id, Byte staff_id, String rental_id, String amount, String payment_date, Timestamp last_update) {
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.staff_id = staff_id;
        this.rental_id = rental_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.last_update = last_update;
	}

	public void setpayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}
	public Integer getpayment_id() {
		return this.payment_id;
	}

	public void setcustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getcustomer_id() {
		return this.customer_id;
	}

	public void setstaff_id(Byte staff_id) {
		this.staff_id = staff_id;
	}
	public Byte getstaff_id() {
		return this.staff_id;
	}

	public void setrental_id(String rental_id) {
		this.rental_id = rental_id;
	}
	public String getrental_id() {
		return this.rental_id;
	}

	public void setamount(String amount) {
		this.amount = amount;
	}
	public String getamount() {
		return this.amount;
	}

	public void setpayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getpayment_date() {
		return this.payment_date;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}