package System;

import java.io.Serializable;

public class Apply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String sex;
	private String job;
	private double wages;
	private String reason;
	private String emid;
	private String taid;
	
	public Apply(String id, String name, String sex,String job, double wages, String reason, String emid, String taid) {
		super();
		this.id=id;
		this.name = name;
		this.sex = sex;
		this.job=job;
		this.wages = wages;
		this.reason = reason;
		this.emid = emid;
		this.taid = taid;
	}

	public Apply() {
		// TODO 自动生成的构造函数存根
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public double getWages() {
		return wages;
	}

	public void setWages(double wages) {
		this.wages = wages;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEmid() {
		return emid;
	}

	public void setEmid(String emid) {
		this.emid = emid;
	}

	public String getTaid() {
		return taid;
	}

	public void setTaid(String taid) {
		this.taid = taid;
	}


}
