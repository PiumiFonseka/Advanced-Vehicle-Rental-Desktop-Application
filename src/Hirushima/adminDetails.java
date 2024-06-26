//admin details

package Hirushima;

import java.sql.Date;

public class adminDetails {
	private int id;
	private String name;
	private String dob;
	private int phone;
	private String street;
	private String city;
	private String nic;
	private String email;
	private String username;
	private String password;
	
	public adminDetails(int id, String name, String dob, int phone,String street,  String city, String nic, String email,String username, String password) {
		super();
		this.id=id;
		this.name=name;
		this.dob=dob;
		this.phone=phone;
		this.street=street;
		this.city=city;
		this.nic=nic;
		this.email=email;
		this.username=username;
		this.password=password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getDOB() {
		return dob;
	}
	public void setDOB(String dob) {
		this.dob=dob;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone=phone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street=street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city=city;
	}
	public String getNIC() {
		return nic;
	}
	public void setNIC(String nic) {
		this.nic=nic;
	}
	public String getEmail() {
		return email;	
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
}


