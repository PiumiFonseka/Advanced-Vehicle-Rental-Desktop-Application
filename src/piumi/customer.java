package piumi;


public class customer {
	private int id;
	private String name;
	private String email;
	private String nic;
	private int phone;
	private String address;
	private String emgName;
	private int emgphone;

	// Constructor
	public customer(int id, String name, String email, String nic,int phone,
			String address, String emgName, int emgphone ) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.nic = nic;
		this.phone = phone;
		this.address = address;	
		this.emgName = emgName;
		this.emgphone = emgphone;

	}

	// Getters and Setters
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
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNic() {
		return nic;
	}


	public void setNic(String nic) {
		this.nic = nic;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmgName() {
		return emgName;
	}


	public void setEmgName(String emgName) {
		this.emgName = emgName;
	}




	public int getEmgphone() {
		return emgphone;
	}


	public void setEmgphone(int emgphone) {
		this.emgphone = emgphone;
	}


	
	
}