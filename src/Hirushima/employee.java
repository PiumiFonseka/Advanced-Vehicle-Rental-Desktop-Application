//employee
package Hirushima;

public class employee {
	private int e_id;
	private String e_name;
	private String e_nic;
	private String e_dob;
	private int e_phone;
	private String e_address;
	private String e_email;
	private String e_position;

	//constructor
	public employee(int e_id,String e_name, String e_nic,String e_dob, int e_phone, String e_address, String e_email, String e_position) {
		super();
		this.e_id= e_id;
		this.e_name=e_name;
		this.e_nic=e_nic;
		this.e_dob=e_dob;
		this.e_phone=e_phone;
		this.e_address=e_address;
		this.e_email=e_email;
		this.e_position=e_position;
	}

	

	public int getEid() {
		return e_id;
	}

	public void setEid(int e_id) {
		this.e_id = e_id;
	}
	public String getName() {
		return e_name;
	}

	public void setName(String e_name) {
		this.e_name = e_name;
	}

	public String getNIC() {
		return e_nic;
	}

	public void setNIC(String e_nic) {
		this.e_nic = e_nic;
	}


	public String getDOB() {
		return e_dob;
	}

	public void setDOB(String e_dob) {
		this.e_dob = e_dob;
	}

	public int getPhone() {
		return e_phone;
	}

	public void setPhone(int e_phone) {
		this.e_phone = e_phone;
	}

	public String getAddress() {
		return e_address;
	}

	public void setAddress(String e_address) {
		this.e_address = e_address;
	}


	public String getEmail() {
		return e_email;
	}

	public void setEmail(String e_email) {
		this.e_email = e_email;

	}
	public String getPosition() {
		return e_position;
	}

	public void setPosition(String e_position) {
		this.e_position = e_position;
	}
	
}
