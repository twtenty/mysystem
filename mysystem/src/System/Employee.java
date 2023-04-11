package System;


public class Employee extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double wages;//薪水
	private String maid;
	
	public Employee(String name, String id, String passwd,String sex, double wages,String maid) {
		super(name, id, passwd,sex);
		this.wages = wages;
		this.maid=maid;
	}
	
	public Employee() {
		// TODO 自动生成的构造函数存根
	}

	public double getWages() {
		return wages;
	}

	public void setWages(double wages) {
		this.wages = wages;
	}
	
	
	public String getMaid() {
		return maid;
	}

	public void setMaid(String maid) {
		this.maid = maid;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		if(this == obj) return true;
		if(obj instanceof Employee)
		{
			Employee b=(Employee) obj;
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
