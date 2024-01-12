package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class store {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte store_id;
	@NotNull
    private Byte manager_staff_id;
	@NotNull
    private Integer address_id;
	@NotNull
    private Timestamp last_update;

    public store() {
    }

    public store(Byte store_id, Byte manager_staff_id, Integer address_id, Timestamp last_update) {
        this.store_id = store_id;
        this.manager_staff_id = manager_staff_id;
        this.address_id = address_id;
        this.last_update = last_update;
	}

	public void setstore_id(Byte store_id) {
		this.store_id = store_id;
	}
	public Byte getstore_id() {
		return this.store_id;
	}

	public void setmanager_staff_id(Byte manager_staff_id) {
		this.manager_staff_id = manager_staff_id;
	}
	public Byte getmanager_staff_id() {
		return this.manager_staff_id;
	}

	public void setaddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getaddress_id() {
		return this.address_id;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}