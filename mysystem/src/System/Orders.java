package System;

public class Orders {
	private String orid;//一个订单对应一个顾客
	private int option;//是否需要派送
	private double price;
	private String cuid;
	private int flag;
	
	public Orders() {
		// TODO 自动生成的构造函数存根
	}

	public Orders(String orid, int option, double price,String cuid,int flag) {
		super();
		this.orid = orid;
		this.option = option;
		this.price = price;
		this.cuid=cuid;
		this.flag=flag;
	}
	
	public int isFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public int isOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
}
