package Hiruni;

// Class representing a Car
public class Cars {
	// Attributes of a car
	private int id; // Unique identifier for the car
	private String brand; // Brand of the car
	private String fuelType; // Type of fuel the car uses
	private String color; // Color of the car
	private int passengers; // Number of passengers the car can accommodate
	private String gearbox; // Type of gearbox
	private double price; // Price of the car
	private String regNumber; // Registration number of the car
	private String vehicleType; // Type of the vehicle

	// Constructor to initialize a Car object
	public Cars(int id, String brand, String fuelType, String color, int passengers, String gearbox, double price,
			String regNumber, String vehicleType) {
		// Initialize attributes with provided values
		this.id = id;
		this.brand = brand;
		this.fuelType = fuelType;
		this.color = color;
		this.passengers = passengers;
		this.gearbox = gearbox;
		this.price = price;
		this.regNumber = regNumber;
		this.vehicleType = vehicleType;
	}

	// Getter method for the car's ID
	public int getId() {
		return id;
	}

	// Setter method for the car's ID
	public void setId(int id) {
		this.id = id;
	}

	// Getter method for the car's brand
	public String getBrand() {
		return brand;
	}

	// Setter method for the car's brand
	public void setBrand(String brand) {
		this.brand = brand;
	}

	// Getter method for the car's fuel type
	public String getFuelType() {
		return fuelType;
	}

	// Setter method for the car's fuel type
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	// Getter method for the car's color
	public String getColor() {
		return color;
	}

	// Setter method for the car's color
	public void setColor(String color) {
		this.color = color;
	}

	// Getter method for the number of passengers the car can accommodate
	public int getPassengers() {
		return passengers;
	}

	// Setter method for the number of passengers the car can accommodate
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	// Getter method for the car's gearbox type
	public String getGearbox() {
		return gearbox;
	}

	// Setter method for the car's gearbox type
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	// Getter method for the car's price
	public double getPrice() {
		return price;
	}

	// Setter method for the car's price
	public void setPrice(double price) {
		this.price = price;
	}

	// Getter method for the car's registration number
	public String getRegNumber() {
		return regNumber;
	}

	// Setter method for the car's registration number
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	// Getter method for the type of the vehicle
	public String getVehicleType() {
		return vehicleType;
	}

	// Setter method for the type of the vehicle
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
