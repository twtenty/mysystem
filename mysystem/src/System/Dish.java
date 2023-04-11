package System;

public class Dish {
	private String id;
	private String me;
	private double price;
	private String orid;
	
	public Dish() {
		
	}
	
	public Dish(String id, String me, double price,String orid) {
		super();
		this.id = id;
		this.me = me;
		this.price = price;
		this.orid=orid;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getorid() {
		return orid;
	}

	public void setorid(String orid) {
		this.orid = orid;
	}
}
