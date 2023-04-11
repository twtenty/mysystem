package System;

public class Menus {
	private String menu;
	private double price;
	
	public Menus(String menu, double price) {
		super();
		this.menu = menu;
		this.price = price;
	}

	public Menus() {
		// TODO 自动生成的构造函数存根
	}
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
