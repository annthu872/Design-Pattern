package com.example.testbasicform;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class address {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer address_id;
	@NotNull
    private String address;
	@NotNull
    private String address2;
	@NotNull
    private String district;
	@NotNull
    private Integer city_id;
	@NotNull
    private String postal_code;
	@NotNull
    private String phone;
	@NotNull
    private String location;
	@NotNull
    private Timestamp last_update;

    public address() {
    }

    public address(Integer address_id, String address, String address2, String district, Integer city_id, String postal_code, String phone, String location, Timestamp last_update) {
        this.address_id = address_id;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.city_id = city_id;
        this.postal_code = postal_code;
        this.phone = phone;
        this.location = location;
        this.last_update = last_update;
	}

	public void setaddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getaddress_id() {
		return this.address_id;
	}

	public void setaddress(String address) {
		this.address = address;
	}
	public String getaddress() {
		return this.address;
	}

	public void setaddress2(String address2) {
		this.address2 = address2;
	}
	public String getaddress2() {
		return this.address2;
	}

	public void setdistrict(String district) {
		this.district = district;
	}
	public String getdistrict() {
		return this.district;
	}

	public void setcity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public Integer getcity_id() {
		return this.city_id;
	}

	public void setpostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getpostal_code() {
		return this.postal_code;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}
	public String getphone() {
		return this.phone;
	}

	public void setlocation(String location) {
		this.location = location;
	}
	public String getlocation() {
		return this.location;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}