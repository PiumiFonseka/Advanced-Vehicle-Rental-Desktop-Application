package piumi;


public class driver {
	private int id;
	private String name;
	private String nic;
	private int phone;
	private String address;
	private String licNum;
	private String licExpDate;
	private double regFee;
	private String note;
	//private String vehType;
	private Boolean car;
	private Boolean van;
	private Boolean lorry;
	private Boolean bus;
	private Boolean jeep;
	private String licType;
	private String Availability;
	
	// Constructor
	public driver( int id, String name, String nic, int phone,  String address, String licNum, 
	String licExpDate, double regFee, String note, Boolean car, Boolean van, Boolean lorry, Boolean bus, Boolean jeep, String licType, String Availability ) {

		this.id = id;
		this.name = name;
		this.nic = nic;
		this.phone = phone;	
		this.address = address;	
		this.licNum = licNum;	
		this.licExpDate = licExpDate;	
		this.regFee = regFee;	
		this.note = note;
		this.car = car;
		this.van = van;
		this.lorry = lorry;
		this.bus = bus;
		this.jeep = jeep;
		this.licType = licType;
		this.Availability = Availability;

	}


	//Getters and Setters
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


	public String getLicNum() {
		return licNum;
	}


	public void setLicNum(String licNum) {
		this.licNum = licNum;
	}


	public String getLicExpDate() {
		return licExpDate;
	}


	public void setLicExpDate(String licExpDate) {
		this.licExpDate = licExpDate;
	}


	public double getRegFee() {
		return regFee;
	}


	public void setRegFee(double regFee) {
		this.regFee = regFee;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	//vehicle type not used instead used car van
/*
 * 	public String getVehType() {
 * 	return vehType;
	}


	public void setVehType(String vehType) {
		this.vehType = vehType;
	}

 */
	

	public Boolean getCar() {
		return car;
	}


	public void setCar(Boolean car) {
		this.car = car;
	}


	public Boolean getVan() {
		return van;
	}


	public void setVan(Boolean van) {
		this.van = van;
	}


	public Boolean getLorry() {
		return lorry;
	}


	public void setLorry(Boolean lorry) {
		this.lorry = lorry;
	}


	public Boolean getBus() {
		return bus;
	}


	public void setBus(Boolean bus) {
		this.bus = bus;
	}


	public Boolean getJeep() {
		return jeep;
	}


	public void setJeep(Boolean jeep) {
		this.jeep = jeep;
	}


	public String getLicType() {
		return licType;
	}


	public void setLicType(String licType) {
		this.licType = licType;
	}


	public String getAvailability() {
		return Availability;
	}


	public void setAvailability(String availability) {
		Availability = availability;
	}



	
	
	
	
}
