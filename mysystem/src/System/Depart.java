package System;
import java.util.Date;

public class Depart extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	private Date date;
	
	public Depart(String name, String id, String passwd,String sex ,String reason, Date date) {
		super(name, id, passwd,sex);
		this.reason = reason;
		this.date = date;
	}

	public Depart() {
		// TODO 自动生成的构造函数存根
	}

	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		if(this == obj) return true;
		if(obj instanceof Depart)
		{
			Depart b=(Depart) obj;
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
