//salary
package Hirushima;

public class salary {
	private int e_id;
	private String month;
	private double rate;
	private String attendance;
	private double offers;
	private double salary;

	//constructor
	public salary(int e_id,String month, double rate,String attendance, double offers, double salary) {
		super();
		this.e_id= e_id;
		this.month=month;
		this.rate=rate;
		this.attendance=attendance;
		this.offers=offers;
		this.salary=salary;
	}

	

	public int getEid() {
		return e_id;
	}

	public void setEid(int e_id) {
		this.e_id = e_id;
	}
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}


	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public double getOffers() {
		return offers;
	}

	public void setOffers(double offers) {
		this.offers = offers;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}

