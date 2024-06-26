package Hiruni;

// Class representing Maintenance details for a vehicle
public class Maintenance {
	// Attributes of Maintenance
	private int id; // Unique identifier for the maintenance record
	private String vehi_reg_no; // Vehicle ID for which maintenance is performed
	private String date; // Date of maintenance
	private String servicecenter; // Name of the service center where maintenance was performed
	private double price; // Price of the maintenance service
	// Total price upto now

	// Constructor to initialize a Maintenance object
	public Maintenance(int id, String vehi_reg_no, String date, String servicecenter, double price) {
		// Initialize attributes with provided values
		this.id = id;
		this.vehi_reg_no = vehi_reg_no;
		this.date = date;
		this.servicecenter = servicecenter;
		this.price = price;

	}

	// Getter method for the maintenance record ID
	public int getId() {
		return id;
	}

	// Setter method for the maintenance record ID
	public void setId(int id) {
		this.id = id;
	}

	// Getter method for the vehicle ID
	public String getvehi_reg_no() {
		return vehi_reg_no;
	}

	// Setter method for the vehicle ID
	public void setvehi_reg_no(String vehi_reg_no) {
		this.vehi_reg_no = vehi_reg_no;
	}

	// Getter method for the maintenance date
	public String getDate() {
		return date;
	}

	// Setter method for the maintenance date
	public void setDate(String date) {
		this.date = date;
	}

	// Getter method for the service center name
	public String getServicecenter() {
		return servicecenter;
	}

	// Setter method for the service center name
	public void setServicecenter(String servicecenter) {
		this.servicecenter = servicecenter;
	}

	// Getter method for the maintenance price
	public double getPrice() {
		return price;
	}

	// Setter method for the maintenance price
	public void setPrice(double price) {
		this.price = price;
	}

}
