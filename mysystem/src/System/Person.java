package System;
import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private String sex;
	private String passwd;
	
	public Person(String name, String id, String passwd,String sex) {
		super();
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.sex=sex;
	}

	public Person() {
		// TODO 自动生成的构造函数存根
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	

}
