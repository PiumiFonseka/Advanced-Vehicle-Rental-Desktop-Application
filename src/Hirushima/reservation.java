//reservation
package Hirushima;

public class reservation {
	private int rid;
	private int cid;
	private int vehi_id;
	private String driver_needed;
	private String pick_date;
	private String pick_time;
	private String return_date;
	private String return_time;
	private int days;
	private double cost;

	//constructor
	public reservation(int rid,int cid, int vehi_id,String driver_needed, String pick_date, String pick_time,
			 String return_date, String return_time, int days, double cost) {
		super();
		this.rid = rid;
		this.cid=cid;
		this.vehi_id=vehi_id;
		this.driver_needed = driver_needed;
		this.pick_date = pick_date;
		this.pick_time = pick_time;
		this.return_date = return_date;
		this.return_time = return_time;
		this.days = days;
		this.cost = cost;
	}

	

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getVehiID() {
		return vehi_id;
	}

	public void setVehiID(int vehi_id) {
		this.vehi_id = vehi_id;
	}


	public String getDriver_needed() {
		return driver_needed;
	}

	public void setDriver_needed(String driver_needed) {
		this.driver_needed = driver_needed;
	}

	public String getPick_date() {
		return pick_date;
	}

	public void setPick_date(String pick_date) {
		this.pick_date = pick_date;
	}

	public String getPick_time() {
		return pick_time;
	}

	public void setPick_time(String pick_time) {
		this.pick_time = pick_time;
	}


	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;

	}
	public String getReturn_time() {
		return return_time;
	}

	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}