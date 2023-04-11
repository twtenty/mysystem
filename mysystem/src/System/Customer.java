package System;

public class Customer extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number;
	private String address;
	private int flag;//是否未完成消费
	
	public Customer(String name, String id,  String passwd,String sex,String number,String address, int flag) {
		super(name, id, passwd,sex);
		this.number=number;
		this.address=address;
		this.flag = flag; 
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Customer() {
		// TODO 自动生成的构造函数存根
	}

	public int isFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		if(this == obj) return true;
		if(obj instanceof Customer)
		{
			Customer b=(Customer) obj;
			if(this.getId().equals(b.getId()))
				return true;
			else return false;
		}
		else return false;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return this.getId()+" "+this.getName();
	}
	
	
}
