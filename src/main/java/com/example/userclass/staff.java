package com.example.userclass;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

public class staff {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte staff_id;
	@NotNull
    private String first_name;
	@NotNull
    private String last_name;
	@NotNull
    private Integer address_id;
	@NotNull
    private String picture;
	@NotNull
    private String email;
	@NotNull
    private Byte store_id;
	@NotNull
    private Boolean active;
	@NotNull
    private String username;
	@NotNull
    private String password;
	@NotNull
    private Timestamp last_update;

    public staff() {
    }

    public staff(Byte staff_id, String first_name, String last_name, Integer address_id, String picture, String email, Byte store_id, Boolean active, String username, String password, Timestamp last_update) {
        this.staff_id = staff_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address_id = address_id;
        this.picture = picture;
        this.email = email;
        this.store_id = store_id;
        this.active = active;
        this.username = username;
        this.password = password;
        this.last_update = last_update;
	}

	public void setstaff_id(Byte staff_id) {
		this.staff_id = staff_id;
	}
	public Byte getstaff_id() {
		return this.staff_id;
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

	public void setaddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getaddress_id() {
		return this.address_id;
	}

	public void setpicture(String picture) {
		this.picture = picture;
	}
	public String getpicture() {
		return this.picture;
	}

	public void setemail(String email) {
		this.email = email;
	}
	public String getemail() {
		return this.email;
	}

	public void setstore_id(Byte store_id) {
		this.store_id = store_id;
	}
	public Byte getstore_id() {
		return this.store_id;
	}

	public void setactive(Boolean active) {
		this.active = active;
	}
	public Boolean getactive() {
		return this.active;
	}

	public void setusername(String username) {
		this.username = username;
	}
	public String getusername() {
		return this.username;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	public String getpassword() {
		return this.password;
	}

	public void setlast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public Timestamp getlast_update() {
		return this.last_update;
	}

}